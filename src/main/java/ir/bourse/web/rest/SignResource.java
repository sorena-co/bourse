package ir.bourse.web.rest;

import ir.bourse.service.SignService;
import ir.bourse.web.rest.errors.BadRequestAlertException;
import ir.bourse.service.dto.SignDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link ir.bourse.domain.Sign}.
 */
@RestController
@RequestMapping("/api")
public class SignResource {

    private final Logger log = LoggerFactory.getLogger(SignResource.class);

    private static final String ENTITY_NAME = "sign";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SignService signService;

    public SignResource(SignService signService) {
        this.signService = signService;
    }

    /**
     * {@code POST  /signs} : Create a new sign.
     *
     * @param signDTO the signDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new signDTO, or with status {@code 400 (Bad Request)} if the sign has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/signs")
    public ResponseEntity<SignDTO> createSign(@RequestBody SignDTO signDTO) throws URISyntaxException {
        log.debug("REST request to save Sign : {}", signDTO);
        if (signDTO.getId() != null) {
            throw new BadRequestAlertException("A new sign cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SignDTO result = signService.save(signDTO);
        return ResponseEntity.created(new URI("/api/signs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /signs} : Updates an existing sign.
     *
     * @param signDTO the signDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated signDTO,
     * or with status {@code 400 (Bad Request)} if the signDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the signDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/signs")
    public ResponseEntity<SignDTO> updateSign(@RequestBody SignDTO signDTO) throws URISyntaxException {
        log.debug("REST request to update Sign : {}", signDTO);
        if (signDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SignDTO result = signService.save(signDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, signDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /signs} : get all the signs.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of signs in body.
     */
    @GetMapping("/signs")
    public ResponseEntity<List<SignDTO>> getAllSigns(Pageable pageable) {
        log.debug("REST request to get a page of Signs");
        Page<SignDTO> page = signService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /signs/:id} : get the "id" sign.
     *
     * @param id the id of the signDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the signDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/signs/{id}")
    public ResponseEntity<SignDTO> getSign(@PathVariable Long id) {
        log.debug("REST request to get Sign : {}", id);
        Optional<SignDTO> signDTO = signService.findOne(id);
        return ResponseUtil.wrapOrNotFound(signDTO);
    }

    /**
     * {@code DELETE  /signs/:id} : delete the "id" sign.
     *
     * @param id the id of the signDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/signs/{id}")
    public ResponseEntity<Void> deleteSign(@PathVariable Long id) {
        log.debug("REST request to delete Sign : {}", id);
        signService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
