package ir.bourse.web.rest;

import ir.bourse.BApp;
import ir.bourse.domain.Sign;
import ir.bourse.repository.SignRepository;
import ir.bourse.service.SignService;
import ir.bourse.service.dto.SignDTO;
import ir.bourse.service.mapper.SignMapper;
import ir.bourse.web.rest.errors.ExceptionTranslator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.util.List;

import static ir.bourse.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link SignResource} REST controller.
 */
@SpringBootTest(classes = BApp.class)
public class SignResourceIT {

    private static final Long DEFAULT_INSTRUMENT_ID = 1L;
    private static final Long UPDATED_INSTRUMENT_ID = 2L;

    private static final String DEFAULT_INSTRUMENT_ISIN = "AAAAAAAAAA";
    private static final String UPDATED_INSTRUMENT_ISIN = "BBBBBBBBBB";

    private static final String DEFAULT_INSTRUMENT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_INSTRUMENT_NAME = "BBBBBBBBBB";

    @Autowired
    private SignRepository signRepository;

    @Autowired
    private SignMapper signMapper;

    @Autowired
    private SignService signService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restSignMockMvc;

    private Sign sign;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final SignResource signResource = new SignResource(signService);
        this.restSignMockMvc = MockMvcBuilders.standaloneSetup(signResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Sign createEntity(EntityManager em) {
        Sign sign = new Sign()
            .instrumentId(DEFAULT_INSTRUMENT_ID)
            .instrumentIsin(DEFAULT_INSTRUMENT_ISIN)
            .instrumentName(DEFAULT_INSTRUMENT_NAME);
        return sign;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Sign createUpdatedEntity(EntityManager em) {
        Sign sign = new Sign()
            .instrumentId(UPDATED_INSTRUMENT_ID)
            .instrumentIsin(UPDATED_INSTRUMENT_ISIN)
            .instrumentName(UPDATED_INSTRUMENT_NAME);
        return sign;
    }

    @BeforeEach
    public void initTest() {
        sign = createEntity(em);
    }

    @Test
    @Transactional
    public void createSign() throws Exception {
        int databaseSizeBeforeCreate = signRepository.findAll().size();

        // Create the Sign
        SignDTO signDTO = signMapper.toDto(sign);
        restSignMockMvc.perform(post("/api/signs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(signDTO)))
            .andExpect(status().isCreated());

        // Validate the Sign in the database
        List<Sign> signList = signRepository.findAll();
        assertThat(signList).hasSize(databaseSizeBeforeCreate + 1);
        Sign testSign = signList.get(signList.size() - 1);
        assertThat(testSign.getInstrumentId()).isEqualTo(DEFAULT_INSTRUMENT_ID);
        assertThat(testSign.getInstrumentIsin()).isEqualTo(DEFAULT_INSTRUMENT_ISIN);
        assertThat(testSign.getInstrumentName()).isEqualTo(DEFAULT_INSTRUMENT_NAME);
    }

    @Test
    @Transactional
    public void createSignWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = signRepository.findAll().size();

        // Create the Sign with an existing ID
        sign.setId(1L);
        SignDTO signDTO = signMapper.toDto(sign);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSignMockMvc.perform(post("/api/signs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(signDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Sign in the database
        List<Sign> signList = signRepository.findAll();
        assertThat(signList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllSigns() throws Exception {
        // Initialize the database
        signRepository.saveAndFlush(sign);

        // Get all the signList
        restSignMockMvc.perform(get("/api/signs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(sign.getId().intValue())))
            .andExpect(jsonPath("$.[*].instrumentId").value(hasItem(DEFAULT_INSTRUMENT_ID.intValue())))
            .andExpect(jsonPath("$.[*].instrumentIsin").value(hasItem(DEFAULT_INSTRUMENT_ISIN)))
            .andExpect(jsonPath("$.[*].instrumentName").value(hasItem(DEFAULT_INSTRUMENT_NAME)));
    }
    
    @Test
    @Transactional
    public void getSign() throws Exception {
        // Initialize the database
        signRepository.saveAndFlush(sign);

        // Get the sign
        restSignMockMvc.perform(get("/api/signs/{id}", sign.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(sign.getId().intValue()))
            .andExpect(jsonPath("$.instrumentId").value(DEFAULT_INSTRUMENT_ID.intValue()))
            .andExpect(jsonPath("$.instrumentIsin").value(DEFAULT_INSTRUMENT_ISIN))
            .andExpect(jsonPath("$.instrumentName").value(DEFAULT_INSTRUMENT_NAME));
    }

    @Test
    @Transactional
    public void getNonExistingSign() throws Exception {
        // Get the sign
        restSignMockMvc.perform(get("/api/signs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSign() throws Exception {
        // Initialize the database
        signRepository.saveAndFlush(sign);

        int databaseSizeBeforeUpdate = signRepository.findAll().size();

        // Update the sign
        Sign updatedSign = signRepository.findById(sign.getId()).get();
        // Disconnect from session so that the updates on updatedSign are not directly saved in db
        em.detach(updatedSign);
        updatedSign
            .instrumentId(UPDATED_INSTRUMENT_ID)
            .instrumentIsin(UPDATED_INSTRUMENT_ISIN)
            .instrumentName(UPDATED_INSTRUMENT_NAME);
        SignDTO signDTO = signMapper.toDto(updatedSign);

        restSignMockMvc.perform(put("/api/signs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(signDTO)))
            .andExpect(status().isOk());

        // Validate the Sign in the database
        List<Sign> signList = signRepository.findAll();
        assertThat(signList).hasSize(databaseSizeBeforeUpdate);
        Sign testSign = signList.get(signList.size() - 1);
        assertThat(testSign.getInstrumentId()).isEqualTo(UPDATED_INSTRUMENT_ID);
        assertThat(testSign.getInstrumentIsin()).isEqualTo(UPDATED_INSTRUMENT_ISIN);
        assertThat(testSign.getInstrumentName()).isEqualTo(UPDATED_INSTRUMENT_NAME);
    }

    @Test
    @Transactional
    public void updateNonExistingSign() throws Exception {
        int databaseSizeBeforeUpdate = signRepository.findAll().size();

        // Create the Sign
        SignDTO signDTO = signMapper.toDto(sign);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSignMockMvc.perform(put("/api/signs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(signDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Sign in the database
        List<Sign> signList = signRepository.findAll();
        assertThat(signList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSign() throws Exception {
        // Initialize the database
        signRepository.saveAndFlush(sign);

        int databaseSizeBeforeDelete = signRepository.findAll().size();

        // Delete the sign
        restSignMockMvc.perform(delete("/api/signs/{id}", sign.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Sign> signList = signRepository.findAll();
        assertThat(signList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
