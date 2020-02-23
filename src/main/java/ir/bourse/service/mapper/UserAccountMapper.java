package ir.bourse.service.mapper;

import ir.bourse.domain.*;
import ir.bourse.service.dto.UserAccountDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link UserAccount} and its DTO {@link UserAccountDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface UserAccountMapper extends EntityMapper<UserAccountDTO, UserAccount> {



    default UserAccount fromId(Long id) {
        if (id == null) {
            return null;
        }
        UserAccount userAccount = new UserAccount();
        userAccount.setId(id);
        return userAccount;
    }
}
