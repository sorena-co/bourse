package ir.bourse.service.mapper;

import ir.bourse.domain.*;
import ir.bourse.service.dto.OrderRequestDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link OrderRequest} and its DTO {@link OrderRequestDTO}.
 */
@Mapper(componentModel = "spring", uses = {SignMapper.class, UserAccountMapper.class})
public interface OrderRequestMapper extends EntityMapper<OrderRequestDTO, OrderRequest> {

    @Mapping(source = "sign.id", target = "signId")
    @Mapping(source = "sign.instrumentName", target = "signInstrumentName")
    @Mapping(source = "userAccount.id", target = "userAccountId")
    @Mapping(source = "userAccount.username", target = "userAccountUsername")
    OrderRequestDTO toDto(OrderRequest orderRequest);

    @Mapping(source = "signId", target = "sign")
    @Mapping(source = "userAccountId", target = "userAccount")
    OrderRequest toEntity(OrderRequestDTO orderRequestDTO);

    default OrderRequest fromId(Long id) {
        if (id == null) {
            return null;
        }
        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setId(id);
        return orderRequest;
    }
}
