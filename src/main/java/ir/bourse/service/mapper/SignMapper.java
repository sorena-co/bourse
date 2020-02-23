package ir.bourse.service.mapper;

import ir.bourse.domain.*;
import ir.bourse.service.dto.SignDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Sign} and its DTO {@link SignDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface SignMapper extends EntityMapper<SignDTO, Sign> {



    default Sign fromId(Long id) {
        if (id == null) {
            return null;
        }
        Sign sign = new Sign();
        sign.setId(id);
        return sign;
    }
}
