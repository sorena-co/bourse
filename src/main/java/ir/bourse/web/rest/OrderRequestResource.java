package ir.bourse.web.rest;

import ir.bourse.service.OrderRequestService;
import ir.bourse.web.rest.errors.BadRequestAlertException;
import ir.bourse.service.dto.OrderRequestDTO;

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
 * REST controller for managing {@link ir.bourse.domain.OrderRequest}.
 */
@RestController
@RequestMapping("/api")
public class OrderRequestResource {

    private final Logger log = LoggerFactory.getLogger(OrderRequestResource.class);

    private static final String ENTITY_NAME = "orderRequest";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final OrderRequestService orderRequestService;

    public OrderRequestResource(OrderRequestService orderRequestService) {
        this.orderRequestService = orderRequestService;
    }

    /**
     * {@code POST  /order-requests} : Create a new orderRequest.
     *
     * @param orderRequestDTO the orderRequestDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new orderRequestDTO, or with status {@code 400 (Bad Request)} if the orderRequest has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/order-requests")
    public ResponseEntity<OrderRequestDTO> createOrderRequest(@RequestBody OrderRequestDTO orderRequestDTO) throws URISyntaxException {
        log.debug("REST request to save OrderRequest : {}", orderRequestDTO);
        if (orderRequestDTO.getId() != null) {
            throw new BadRequestAlertException("A new orderRequest cannot already have an ID", ENTITY_NAME, "idexists");
        }
        OrderRequestDTO result = orderRequestService.save(orderRequestDTO);
        return ResponseEntity.created(new URI("/api/order-requests/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /order-requests} : Updates an existing orderRequest.
     *
     * @param orderRequestDTO the orderRequestDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated orderRequestDTO,
     * or with status {@code 400 (Bad Request)} if the orderRequestDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the orderRequestDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/order-requests")
    public ResponseEntity<OrderRequestDTO> updateOrderRequest(@RequestBody OrderRequestDTO orderRequestDTO) throws URISyntaxException {
        log.debug("REST request to update OrderRequest : {}", orderRequestDTO);
        if (orderRequestDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        OrderRequestDTO result = orderRequestService.save(orderRequestDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, orderRequestDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /order-requests} : get all the orderRequests.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of orderRequests in body.
     */
    @GetMapping("/order-requests")
    public ResponseEntity<List<OrderRequestDTO>> getAllOrderRequests(Pageable pageable) {
        log.debug("REST request to get a page of OrderRequests");
        Page<OrderRequestDTO> page = orderRequestService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /order-requests/:id} : get the "id" orderRequest.
     *
     * @param id the id of the orderRequestDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the orderRequestDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/order-requests/{id}")
    public ResponseEntity<OrderRequestDTO> getOrderRequest(@PathVariable Long id) {
        log.debug("REST request to get OrderRequest : {}", id);
        Optional<OrderRequestDTO> orderRequestDTO = orderRequestService.findOne(id);
        return ResponseUtil.wrapOrNotFound(orderRequestDTO);
    }

    /**
     * {@code DELETE  /order-requests/:id} : delete the "id" orderRequest.
     *
     * @param id the id of the orderRequestDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/order-requests/{id}")
    public ResponseEntity<Void> deleteOrderRequest(@PathVariable Long id) {
        log.debug("REST request to delete OrderRequest : {}", id);
        orderRequestService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
