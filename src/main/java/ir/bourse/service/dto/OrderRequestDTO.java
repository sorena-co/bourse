package ir.bourse.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link ir.bourse.domain.OrderRequest} entity.
 */
public class OrderRequestDTO implements Serializable {

    private Long id;

    private Long customerId;

    private String customerTitle;

    private String orderSide;

    private Long orderSideId;

    private Long price;

    private Long quantity;

    private Long value;

    private String validityDate;

    private Long minimumQuantity;

    private Long disclosedQuantity;

    private Long validityType;

    private Long bankAccountId;

    private Long expectedRemainingQuantity;

    private Long tradedQuantity;

    private String categoryId;

    private Long remainingQuantity;

    private Long orderExecuterId;


    private Long signId;

    private String signInstrumentName;

    private String signInstrumentIsin;

    private Long userAccountId;

    private String userAccountUsername;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getCustomerTitle() {
        return customerTitle;
    }

    public void setCustomerTitle(String customerTitle) {
        this.customerTitle = customerTitle;
    }

    public String getOrderSide() {
        return orderSide;
    }

    public void setOrderSide(String orderSide) {
        this.orderSide = orderSide;
    }

    public Long getOrderSideId() {
        return orderSideId;
    }

    public void setOrderSideId(Long orderSideId) {
        this.orderSideId = orderSideId;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }

    public String getValidityDate() {
        return validityDate;
    }

    public void setValidityDate(String validityDate) {
        this.validityDate = validityDate;
    }

    public Long getMinimumQuantity() {
        return minimumQuantity;
    }

    public void setMinimumQuantity(Long minimumQuantity) {
        this.minimumQuantity = minimumQuantity;
    }

    public Long getDisclosedQuantity() {
        return disclosedQuantity;
    }

    public void setDisclosedQuantity(Long disclosedQuantity) {
        this.disclosedQuantity = disclosedQuantity;
    }

    public Long getValidityType() {
        return validityType;
    }

    public void setValidityType(Long validityType) {
        this.validityType = validityType;
    }

    public Long getBankAccountId() {
        return bankAccountId;
    }

    public void setBankAccountId(Long bankAccountId) {
        this.bankAccountId = bankAccountId;
    }

    public Long getExpectedRemainingQuantity() {
        return expectedRemainingQuantity;
    }

    public void setExpectedRemainingQuantity(Long expectedRemainingQuantity) {
        this.expectedRemainingQuantity = expectedRemainingQuantity;
    }

    public Long getTradedQuantity() {
        return tradedQuantity;
    }

    public void setTradedQuantity(Long tradedQuantity) {
        this.tradedQuantity = tradedQuantity;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public Long getRemainingQuantity() {
        return remainingQuantity;
    }

    public void setRemainingQuantity(Long remainingQuantity) {
        this.remainingQuantity = remainingQuantity;
    }

    public Long getOrderExecuterId() {
        return orderExecuterId;
    }

    public void setOrderExecuterId(Long orderExecuterId) {
        this.orderExecuterId = orderExecuterId;
    }

    public Long getSignId() {
        return signId;
    }

    public void setSignId(Long signId) {
        this.signId = signId;
    }

    public String getSignInstrumentName() {
        return signInstrumentName;
    }

    public void setSignInstrumentName(String signInstrumentName) {
        this.signInstrumentName = signInstrumentName;
    }

    public Long getUserAccountId() {
        return userAccountId;
    }

    public void setUserAccountId(Long userAccountId) {
        this.userAccountId = userAccountId;
    }

    public String getUserAccountUsername() {
        return userAccountUsername;
    }

    public void setUserAccountUsername(String userAccountUsername) {
        this.userAccountUsername = userAccountUsername;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        OrderRequestDTO orderRequestDTO = (OrderRequestDTO) o;
        if (orderRequestDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), orderRequestDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "OrderRequestDTO{" +
            "id=" + getId() +
            ", customerId=" + getCustomerId() +
            ", customerTitle='" + getCustomerTitle() + "'" +
            ", orderSide='" + getOrderSide() + "'" +
            ", orderSideId=" + getOrderSideId() +
            ", price=" + getPrice() +
            ", quantity=" + getQuantity() +
            ", value=" + getValue() +
            ", validityDate='" + getValidityDate() + "'" +
            ", minimumQuantity=" + getMinimumQuantity() +
            ", disclosedQuantity=" + getDisclosedQuantity() +
            ", validityType=" + getValidityType() +
            ", bankAccountId=" + getBankAccountId() +
            ", expectedRemainingQuantity=" + getExpectedRemainingQuantity() +
            ", tradedQuantity=" + getTradedQuantity() +
            ", categoryId='" + getCategoryId() + "'" +
            ", remainingQuantity=" + getRemainingQuantity() +
            ", orderExecuterId=" + getOrderExecuterId() +
            ", sign=" + getSignId() +
            ", sign='" + getSignInstrumentName() + "'" +
            ", userAccount=" + getUserAccountId() +
            ", userAccount='" + getUserAccountUsername() + "'" +
            "}";
    }

    public String getSignInstrumentIsin() {
        return signInstrumentIsin;
    }

    public OrderRequestDTO setSignInstrumentIsin(String signInstrumentIsin) {
        this.signInstrumentIsin = signInstrumentIsin;
        return this;
    }
}
