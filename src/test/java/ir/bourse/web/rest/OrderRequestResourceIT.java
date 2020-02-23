package ir.bourse.web.rest;

import ir.bourse.BApp;
import ir.bourse.domain.OrderRequest;
import ir.bourse.repository.OrderRequestRepository;
import ir.bourse.service.OrderRequestService;
import ir.bourse.service.dto.OrderRequestDTO;
import ir.bourse.service.mapper.OrderRequestMapper;
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
 * Integration tests for the {@link OrderRequestResource} REST controller.
 */
@SpringBootTest(classes = BApp.class)
public class OrderRequestResourceIT {

    private static final Long DEFAULT_CUSTOMER_ID = 1L;
    private static final Long UPDATED_CUSTOMER_ID = 2L;

    private static final String DEFAULT_CUSTOMER_TITLE = "AAAAAAAAAA";
    private static final String UPDATED_CUSTOMER_TITLE = "BBBBBBBBBB";

    private static final String DEFAULT_ORDER_SIDE = "AAAAAAAAAA";
    private static final String UPDATED_ORDER_SIDE = "BBBBBBBBBB";

    private static final Long DEFAULT_ORDER_SIDE_ID = 1L;
    private static final Long UPDATED_ORDER_SIDE_ID = 2L;

    private static final Long DEFAULT_PRICE = 1L;
    private static final Long UPDATED_PRICE = 2L;

    private static final Long DEFAULT_QUANTITY = 1L;
    private static final Long UPDATED_QUANTITY = 2L;

    private static final Long DEFAULT_VALUE = 1L;
    private static final Long UPDATED_VALUE = 2L;

    private static final String DEFAULT_VALIDITY_DATE = "AAAAAAAAAA";
    private static final String UPDATED_VALIDITY_DATE = "BBBBBBBBBB";

    private static final Long DEFAULT_MINIMUM_QUANTITY = 1L;
    private static final Long UPDATED_MINIMUM_QUANTITY = 2L;

    private static final Long DEFAULT_DISCLOSED_QUANTITY = 1L;
    private static final Long UPDATED_DISCLOSED_QUANTITY = 2L;

    private static final Long DEFAULT_VALIDITY_TYPE = 1L;
    private static final Long UPDATED_VALIDITY_TYPE = 2L;

    private static final Long DEFAULT_BANK_ACCOUNT_ID = 1L;
    private static final Long UPDATED_BANK_ACCOUNT_ID = 2L;

    private static final Long DEFAULT_EXPECTED_REMAINING_QUANTITY = 1L;
    private static final Long UPDATED_EXPECTED_REMAINING_QUANTITY = 2L;

    private static final Long DEFAULT_TRADED_QUANTITY = 1L;
    private static final Long UPDATED_TRADED_QUANTITY = 2L;

    private static final String DEFAULT_CATEGORY_ID = "AAAAAAAAAA";
    private static final String UPDATED_CATEGORY_ID = "BBBBBBBBBB";

    private static final Long DEFAULT_REMAINING_QUANTITY = 1L;
    private static final Long UPDATED_REMAINING_QUANTITY = 2L;

    private static final Long DEFAULT_ORDER_EXECUTER_ID = 1L;
    private static final Long UPDATED_ORDER_EXECUTER_ID = 2L;

    @Autowired
    private OrderRequestRepository orderRequestRepository;

    @Autowired
    private OrderRequestMapper orderRequestMapper;

    @Autowired
    private OrderRequestService orderRequestService;

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

    private MockMvc restOrderRequestMockMvc;

    private OrderRequest orderRequest;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final OrderRequestResource orderRequestResource = new OrderRequestResource(orderRequestService);
        this.restOrderRequestMockMvc = MockMvcBuilders.standaloneSetup(orderRequestResource)
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
    public static OrderRequest createEntity(EntityManager em) {
        OrderRequest orderRequest = new OrderRequest()
            .customerId(DEFAULT_CUSTOMER_ID)
            .customerTitle(DEFAULT_CUSTOMER_TITLE)
            .orderSide(DEFAULT_ORDER_SIDE)
            .orderSideId(DEFAULT_ORDER_SIDE_ID)
            .price(DEFAULT_PRICE)
            .quantity(DEFAULT_QUANTITY)
            .value(DEFAULT_VALUE)
            .validityDate(DEFAULT_VALIDITY_DATE)
            .minimumQuantity(DEFAULT_MINIMUM_QUANTITY)
            .disclosedQuantity(DEFAULT_DISCLOSED_QUANTITY)
            .validityType(DEFAULT_VALIDITY_TYPE)
            .bankAccountId(DEFAULT_BANK_ACCOUNT_ID)
            .expectedRemainingQuantity(DEFAULT_EXPECTED_REMAINING_QUANTITY)
            .tradedQuantity(DEFAULT_TRADED_QUANTITY)
            .categoryId(DEFAULT_CATEGORY_ID)
            .remainingQuantity(DEFAULT_REMAINING_QUANTITY)
            .orderExecuterId(DEFAULT_ORDER_EXECUTER_ID);
        return orderRequest;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OrderRequest createUpdatedEntity(EntityManager em) {
        OrderRequest orderRequest = new OrderRequest()
            .customerId(UPDATED_CUSTOMER_ID)
            .customerTitle(UPDATED_CUSTOMER_TITLE)
            .orderSide(UPDATED_ORDER_SIDE)
            .orderSideId(UPDATED_ORDER_SIDE_ID)
            .price(UPDATED_PRICE)
            .quantity(UPDATED_QUANTITY)
            .value(UPDATED_VALUE)
            .validityDate(UPDATED_VALIDITY_DATE)
            .minimumQuantity(UPDATED_MINIMUM_QUANTITY)
            .disclosedQuantity(UPDATED_DISCLOSED_QUANTITY)
            .validityType(UPDATED_VALIDITY_TYPE)
            .bankAccountId(UPDATED_BANK_ACCOUNT_ID)
            .expectedRemainingQuantity(UPDATED_EXPECTED_REMAINING_QUANTITY)
            .tradedQuantity(UPDATED_TRADED_QUANTITY)
            .categoryId(UPDATED_CATEGORY_ID)
            .remainingQuantity(UPDATED_REMAINING_QUANTITY)
            .orderExecuterId(UPDATED_ORDER_EXECUTER_ID);
        return orderRequest;
    }

    @BeforeEach
    public void initTest() {
        orderRequest = createEntity(em);
    }

    @Test
    @Transactional
    public void createOrderRequest() throws Exception {
        int databaseSizeBeforeCreate = orderRequestRepository.findAll().size();

        // Create the OrderRequest
        OrderRequestDTO orderRequestDTO = orderRequestMapper.toDto(orderRequest);
        restOrderRequestMockMvc.perform(post("/api/order-requests")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(orderRequestDTO)))
            .andExpect(status().isCreated());

        // Validate the OrderRequest in the database
        List<OrderRequest> orderRequestList = orderRequestRepository.findAll();
        assertThat(orderRequestList).hasSize(databaseSizeBeforeCreate + 1);
        OrderRequest testOrderRequest = orderRequestList.get(orderRequestList.size() - 1);
        assertThat(testOrderRequest.getCustomerId()).isEqualTo(DEFAULT_CUSTOMER_ID);
        assertThat(testOrderRequest.getCustomerTitle()).isEqualTo(DEFAULT_CUSTOMER_TITLE);
        assertThat(testOrderRequest.getOrderSide()).isEqualTo(DEFAULT_ORDER_SIDE);
        assertThat(testOrderRequest.getOrderSideId()).isEqualTo(DEFAULT_ORDER_SIDE_ID);
        assertThat(testOrderRequest.getPrice()).isEqualTo(DEFAULT_PRICE);
        assertThat(testOrderRequest.getQuantity()).isEqualTo(DEFAULT_QUANTITY);
        assertThat(testOrderRequest.getValue()).isEqualTo(DEFAULT_VALUE);
        assertThat(testOrderRequest.getValidityDate()).isEqualTo(DEFAULT_VALIDITY_DATE);
        assertThat(testOrderRequest.getMinimumQuantity()).isEqualTo(DEFAULT_MINIMUM_QUANTITY);
        assertThat(testOrderRequest.getDisclosedQuantity()).isEqualTo(DEFAULT_DISCLOSED_QUANTITY);
        assertThat(testOrderRequest.getValidityType()).isEqualTo(DEFAULT_VALIDITY_TYPE);
        assertThat(testOrderRequest.getBankAccountId()).isEqualTo(DEFAULT_BANK_ACCOUNT_ID);
        assertThat(testOrderRequest.getExpectedRemainingQuantity()).isEqualTo(DEFAULT_EXPECTED_REMAINING_QUANTITY);
        assertThat(testOrderRequest.getTradedQuantity()).isEqualTo(DEFAULT_TRADED_QUANTITY);
        assertThat(testOrderRequest.getCategoryId()).isEqualTo(DEFAULT_CATEGORY_ID);
        assertThat(testOrderRequest.getRemainingQuantity()).isEqualTo(DEFAULT_REMAINING_QUANTITY);
        assertThat(testOrderRequest.getOrderExecuterId()).isEqualTo(DEFAULT_ORDER_EXECUTER_ID);
    }

    @Test
    @Transactional
    public void createOrderRequestWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = orderRequestRepository.findAll().size();

        // Create the OrderRequest with an existing ID
        orderRequest.setId(1L);
        OrderRequestDTO orderRequestDTO = orderRequestMapper.toDto(orderRequest);

        // An entity with an existing ID cannot be created, so this API call must fail
        restOrderRequestMockMvc.perform(post("/api/order-requests")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(orderRequestDTO)))
            .andExpect(status().isBadRequest());

        // Validate the OrderRequest in the database
        List<OrderRequest> orderRequestList = orderRequestRepository.findAll();
        assertThat(orderRequestList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllOrderRequests() throws Exception {
        // Initialize the database
        orderRequestRepository.saveAndFlush(orderRequest);

        // Get all the orderRequestList
        restOrderRequestMockMvc.perform(get("/api/order-requests?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(orderRequest.getId().intValue())))
            .andExpect(jsonPath("$.[*].customerId").value(hasItem(DEFAULT_CUSTOMER_ID.intValue())))
            .andExpect(jsonPath("$.[*].customerTitle").value(hasItem(DEFAULT_CUSTOMER_TITLE)))
            .andExpect(jsonPath("$.[*].orderSide").value(hasItem(DEFAULT_ORDER_SIDE)))
            .andExpect(jsonPath("$.[*].orderSideId").value(hasItem(DEFAULT_ORDER_SIDE_ID.intValue())))
            .andExpect(jsonPath("$.[*].price").value(hasItem(DEFAULT_PRICE.intValue())))
            .andExpect(jsonPath("$.[*].quantity").value(hasItem(DEFAULT_QUANTITY.intValue())))
            .andExpect(jsonPath("$.[*].value").value(hasItem(DEFAULT_VALUE.intValue())))
            .andExpect(jsonPath("$.[*].validityDate").value(hasItem(DEFAULT_VALIDITY_DATE)))
            .andExpect(jsonPath("$.[*].minimumQuantity").value(hasItem(DEFAULT_MINIMUM_QUANTITY.intValue())))
            .andExpect(jsonPath("$.[*].disclosedQuantity").value(hasItem(DEFAULT_DISCLOSED_QUANTITY.intValue())))
            .andExpect(jsonPath("$.[*].validityType").value(hasItem(DEFAULT_VALIDITY_TYPE.intValue())))
            .andExpect(jsonPath("$.[*].bankAccountId").value(hasItem(DEFAULT_BANK_ACCOUNT_ID.intValue())))
            .andExpect(jsonPath("$.[*].expectedRemainingQuantity").value(hasItem(DEFAULT_EXPECTED_REMAINING_QUANTITY.intValue())))
            .andExpect(jsonPath("$.[*].tradedQuantity").value(hasItem(DEFAULT_TRADED_QUANTITY.intValue())))
            .andExpect(jsonPath("$.[*].categoryId").value(hasItem(DEFAULT_CATEGORY_ID)))
            .andExpect(jsonPath("$.[*].remainingQuantity").value(hasItem(DEFAULT_REMAINING_QUANTITY.intValue())))
            .andExpect(jsonPath("$.[*].orderExecuterId").value(hasItem(DEFAULT_ORDER_EXECUTER_ID.intValue())));
    }
    
    @Test
    @Transactional
    public void getOrderRequest() throws Exception {
        // Initialize the database
        orderRequestRepository.saveAndFlush(orderRequest);

        // Get the orderRequest
        restOrderRequestMockMvc.perform(get("/api/order-requests/{id}", orderRequest.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(orderRequest.getId().intValue()))
            .andExpect(jsonPath("$.customerId").value(DEFAULT_CUSTOMER_ID.intValue()))
            .andExpect(jsonPath("$.customerTitle").value(DEFAULT_CUSTOMER_TITLE))
            .andExpect(jsonPath("$.orderSide").value(DEFAULT_ORDER_SIDE))
            .andExpect(jsonPath("$.orderSideId").value(DEFAULT_ORDER_SIDE_ID.intValue()))
            .andExpect(jsonPath("$.price").value(DEFAULT_PRICE.intValue()))
            .andExpect(jsonPath("$.quantity").value(DEFAULT_QUANTITY.intValue()))
            .andExpect(jsonPath("$.value").value(DEFAULT_VALUE.intValue()))
            .andExpect(jsonPath("$.validityDate").value(DEFAULT_VALIDITY_DATE))
            .andExpect(jsonPath("$.minimumQuantity").value(DEFAULT_MINIMUM_QUANTITY.intValue()))
            .andExpect(jsonPath("$.disclosedQuantity").value(DEFAULT_DISCLOSED_QUANTITY.intValue()))
            .andExpect(jsonPath("$.validityType").value(DEFAULT_VALIDITY_TYPE.intValue()))
            .andExpect(jsonPath("$.bankAccountId").value(DEFAULT_BANK_ACCOUNT_ID.intValue()))
            .andExpect(jsonPath("$.expectedRemainingQuantity").value(DEFAULT_EXPECTED_REMAINING_QUANTITY.intValue()))
            .andExpect(jsonPath("$.tradedQuantity").value(DEFAULT_TRADED_QUANTITY.intValue()))
            .andExpect(jsonPath("$.categoryId").value(DEFAULT_CATEGORY_ID))
            .andExpect(jsonPath("$.remainingQuantity").value(DEFAULT_REMAINING_QUANTITY.intValue()))
            .andExpect(jsonPath("$.orderExecuterId").value(DEFAULT_ORDER_EXECUTER_ID.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingOrderRequest() throws Exception {
        // Get the orderRequest
        restOrderRequestMockMvc.perform(get("/api/order-requests/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateOrderRequest() throws Exception {
        // Initialize the database
        orderRequestRepository.saveAndFlush(orderRequest);

        int databaseSizeBeforeUpdate = orderRequestRepository.findAll().size();

        // Update the orderRequest
        OrderRequest updatedOrderRequest = orderRequestRepository.findById(orderRequest.getId()).get();
        // Disconnect from session so that the updates on updatedOrderRequest are not directly saved in db
        em.detach(updatedOrderRequest);
        updatedOrderRequest
            .customerId(UPDATED_CUSTOMER_ID)
            .customerTitle(UPDATED_CUSTOMER_TITLE)
            .orderSide(UPDATED_ORDER_SIDE)
            .orderSideId(UPDATED_ORDER_SIDE_ID)
            .price(UPDATED_PRICE)
            .quantity(UPDATED_QUANTITY)
            .value(UPDATED_VALUE)
            .validityDate(UPDATED_VALIDITY_DATE)
            .minimumQuantity(UPDATED_MINIMUM_QUANTITY)
            .disclosedQuantity(UPDATED_DISCLOSED_QUANTITY)
            .validityType(UPDATED_VALIDITY_TYPE)
            .bankAccountId(UPDATED_BANK_ACCOUNT_ID)
            .expectedRemainingQuantity(UPDATED_EXPECTED_REMAINING_QUANTITY)
            .tradedQuantity(UPDATED_TRADED_QUANTITY)
            .categoryId(UPDATED_CATEGORY_ID)
            .remainingQuantity(UPDATED_REMAINING_QUANTITY)
            .orderExecuterId(UPDATED_ORDER_EXECUTER_ID);
        OrderRequestDTO orderRequestDTO = orderRequestMapper.toDto(updatedOrderRequest);

        restOrderRequestMockMvc.perform(put("/api/order-requests")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(orderRequestDTO)))
            .andExpect(status().isOk());

        // Validate the OrderRequest in the database
        List<OrderRequest> orderRequestList = orderRequestRepository.findAll();
        assertThat(orderRequestList).hasSize(databaseSizeBeforeUpdate);
        OrderRequest testOrderRequest = orderRequestList.get(orderRequestList.size() - 1);
        assertThat(testOrderRequest.getCustomerId()).isEqualTo(UPDATED_CUSTOMER_ID);
        assertThat(testOrderRequest.getCustomerTitle()).isEqualTo(UPDATED_CUSTOMER_TITLE);
        assertThat(testOrderRequest.getOrderSide()).isEqualTo(UPDATED_ORDER_SIDE);
        assertThat(testOrderRequest.getOrderSideId()).isEqualTo(UPDATED_ORDER_SIDE_ID);
        assertThat(testOrderRequest.getPrice()).isEqualTo(UPDATED_PRICE);
        assertThat(testOrderRequest.getQuantity()).isEqualTo(UPDATED_QUANTITY);
        assertThat(testOrderRequest.getValue()).isEqualTo(UPDATED_VALUE);
        assertThat(testOrderRequest.getValidityDate()).isEqualTo(UPDATED_VALIDITY_DATE);
        assertThat(testOrderRequest.getMinimumQuantity()).isEqualTo(UPDATED_MINIMUM_QUANTITY);
        assertThat(testOrderRequest.getDisclosedQuantity()).isEqualTo(UPDATED_DISCLOSED_QUANTITY);
        assertThat(testOrderRequest.getValidityType()).isEqualTo(UPDATED_VALIDITY_TYPE);
        assertThat(testOrderRequest.getBankAccountId()).isEqualTo(UPDATED_BANK_ACCOUNT_ID);
        assertThat(testOrderRequest.getExpectedRemainingQuantity()).isEqualTo(UPDATED_EXPECTED_REMAINING_QUANTITY);
        assertThat(testOrderRequest.getTradedQuantity()).isEqualTo(UPDATED_TRADED_QUANTITY);
        assertThat(testOrderRequest.getCategoryId()).isEqualTo(UPDATED_CATEGORY_ID);
        assertThat(testOrderRequest.getRemainingQuantity()).isEqualTo(UPDATED_REMAINING_QUANTITY);
        assertThat(testOrderRequest.getOrderExecuterId()).isEqualTo(UPDATED_ORDER_EXECUTER_ID);
    }

    @Test
    @Transactional
    public void updateNonExistingOrderRequest() throws Exception {
        int databaseSizeBeforeUpdate = orderRequestRepository.findAll().size();

        // Create the OrderRequest
        OrderRequestDTO orderRequestDTO = orderRequestMapper.toDto(orderRequest);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOrderRequestMockMvc.perform(put("/api/order-requests")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(orderRequestDTO)))
            .andExpect(status().isBadRequest());

        // Validate the OrderRequest in the database
        List<OrderRequest> orderRequestList = orderRequestRepository.findAll();
        assertThat(orderRequestList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteOrderRequest() throws Exception {
        // Initialize the database
        orderRequestRepository.saveAndFlush(orderRequest);

        int databaseSizeBeforeDelete = orderRequestRepository.findAll().size();

        // Delete the orderRequest
        restOrderRequestMockMvc.perform(delete("/api/order-requests/{id}", orderRequest.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<OrderRequest> orderRequestList = orderRequestRepository.findAll();
        assertThat(orderRequestList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
