package ir.bourse.service;

import ir.bourse.domain.OrderRequest;
import ir.bourse.repository.OrderRequestRepository;
import ir.bourse.service.dto.OrderRequestDTO;
import ir.bourse.service.mapper.OrderRequestMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link OrderRequest}.
 */
@Service
@Transactional
public class OrderRequestService {

    private final Logger log = LoggerFactory.getLogger(OrderRequestService.class);

    private final OrderRequestRepository orderRequestRepository;

    private final OrderRequestMapper orderRequestMapper;

    public OrderRequestService(OrderRequestRepository orderRequestRepository, OrderRequestMapper orderRequestMapper) {
        this.orderRequestRepository = orderRequestRepository;
        this.orderRequestMapper = orderRequestMapper;
    }

    /**
     * Save a orderRequest.
     *
     * @param orderRequestDTO the entity to save.
     * @return the persisted entity.
     */
    public OrderRequestDTO save(OrderRequestDTO orderRequestDTO) {
        log.debug("Request to save OrderRequest : {}", orderRequestDTO);
        OrderRequest orderRequest = orderRequestMapper.toEntity(orderRequestDTO);
        orderRequest = orderRequestRepository.save(orderRequest);
        return orderRequestMapper.toDto(orderRequest);
    }

    /**
     * Get all the orderRequests.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<OrderRequestDTO> findAll(Pageable pageable) {
        log.debug("Request to get all OrderRequests");
        return orderRequestRepository.findAll(pageable)
            .map(orderRequestMapper::toDto);
    }


    /**
     * Get one orderRequest by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<OrderRequestDTO> findOne(Long id) {
        log.debug("Request to get OrderRequest : {}", id);
        return orderRequestRepository.findById(id)
            .map(orderRequestMapper::toDto);
    }

    /**
     * Delete the orderRequest by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete OrderRequest : {}", id);
        orderRequestRepository.deleteById(id);
    }
}
