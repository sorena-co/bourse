package ir.bourse.service.dto;
import java.io.Serializable;
import java.util.Objects;
import ir.bourse.domain.enumeration.AccountType;

/**
 * A DTO for the {@link ir.bourse.domain.UserAccount} entity.
 */
public class UserAccountDTO implements Serializable {

    private Long id;

    private String firstName;

    private String lastName;

    private AccountType accountType;

    private String username;

    private String password;

    private Boolean active;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean isActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        UserAccountDTO userAccountDTO = (UserAccountDTO) o;
        if (userAccountDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), userAccountDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "UserAccountDTO{" +
            "id=" + getId() +
            ", firstName='" + getFirstName() + "'" +
            ", lastName='" + getLastName() + "'" +
            ", accountType='" + getAccountType() + "'" +
            ", username='" + getUsername() + "'" +
            ", password='" + getPassword() + "'" +
            ", active='" + isActive() + "'" +
            "}";
    }
}
