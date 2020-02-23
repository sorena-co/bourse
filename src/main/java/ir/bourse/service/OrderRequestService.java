package ir.bourse.service;

import ir.bourse.domain.OrderRequest;
import ir.bourse.domain.UserAccount;
import ir.bourse.repository.OrderRequestRepository;
import ir.bourse.repository.UserAccountRepository;
import ir.bourse.service.dto.OrderRequestDTO;
import ir.bourse.service.mapper.OrderRequestMapper;
import ir.bourse.service.mapper.UserAccountMapper;
import net.sourceforge.tess4j.TesseractException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

/**
 * Service Implementation for managing {@link OrderRequest}.
 */
@Service
@Transactional
public class OrderRequestService {

    private final Logger log = LoggerFactory.getLogger(OrderRequestService.class);

    private final OrderRequestRepository orderRequestRepository;
    private final BuyService buyService;
    private final OrderRequestMapper orderRequestMapper;
    private final UserAccountRepository userAccountRepository;
    private final UserAccountMapper userAccountMapper;

    public OrderRequestService(OrderRequestRepository orderRequestRepository, BuyService buyService, OrderRequestMapper orderRequestMapper, UserAccountRepository userAccountRepository, UserAccountMapper userAccountMapper) {
        this.orderRequestRepository = orderRequestRepository;
        this.buyService = buyService;
        this.orderRequestMapper = orderRequestMapper;
        this.userAccountRepository = userAccountRepository;
        this.userAccountMapper = userAccountMapper;
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
        UserAccount userAccount = userAccountRepository.findById(orderRequest.getUserAccount().getId()).get();
        orderRequest.setCustomerTitle(userAccount.getFirstName() + " " + userAccount.getLastName());
        orderRequest = orderRequestRepository.save(orderRequest);
        orderRequest.setOrderSide("Buy");
        orderRequest.setOrderSideId(1L);
        orderRequest.setValue(0L);
        orderRequest.setValidityDate(null);
        orderRequest.setMinimumQuantity(null);
        orderRequest.setDisclosedQuantity(null);
        orderRequest.setValidityType(1L);
        orderRequest.setBankAccountId(0L);
        orderRequest.setExpectedRemainingQuantity(0L);
        orderRequest.setTradedQuantity(0L);
        orderRequest.setCategoryId(UUID.randomUUID().toString());
        orderRequest.setRemainingQuantity(orderRequest.getQuantity());
        orderRequest.setOrderExecuterId(3L);
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

    public Optional<OrderRequestDTO> start(Long id) throws IOException, TesseractException {
        Optional<OrderRequest> orderRequest = orderRequestRepository.findById(id);
        Optional<OrderRequestDTO> orderRequestDTO = orderRequest.map(orderRequestMapper::toDto);
        String captcha = buyService.getCaptcha();
        if (buyService.login(userAccountMapper.toDto(orderRequest.get().getUserAccount()), captcha)) {
            buyService.buy(orderRequestDTO.get(),1);
        }
        return orderRequestDTO;
    }
}
