package ir.bourse.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link ir.bourse.domain.OrderRequest} entity.
 */
public class SendOrderDTO implements Serializable {

    @JsonProperty("Id")
    private Long id;

    @JsonProperty("CustomerId")
    private Long customerId;

    @JsonProperty("CustomerTitle")
    private String customerTitle;

    @JsonProperty("OrderSide")
    private String orderSide;

    @JsonProperty("OrderSideId")
    private Long orderSideId;

    @JsonProperty("Price")
    private Long price;

    @JsonProperty("Quantity")
    private Long quantity;

    @JsonProperty("Value")
    private Long value;

    @JsonProperty("ValidityDat")
    private String validityDate;

    @JsonProperty("MinimumQuantity")
    private Long minimumQuantity;

    @JsonProperty("DisclosedQuantity")
    private Long disclosedQuantity;

    @JsonProperty("ValidityType")
    private Long validityType;

    @JsonProperty("BankAccountId")
    private Long bankAccountId;

    @JsonProperty("ExpectedRemainingQuantity")
    private Long expectedRemainingQuantity;

    @JsonProperty("TradedQuantity")
    private Long tradedQuantity;

    @JsonProperty("CategoryId")
    private String categoryId;

    @JsonProperty("RemainingQuantity")
    private Long remainingQuantity;

    @JsonProperty("OrderExecuterId")
    private Long orderExecuterId;

    @JsonProperty("InstrumentId")
    private Long signId;

    @JsonProperty("InstrumentName")
    private String signInstrumentName;

    @JsonProperty("InstrumentIsin")
    private String signInstrumentIsin;


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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SendOrderDTO orderRequestDTO = (SendOrderDTO) o;
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
            "}";
    }

    public String getSignInstrumentIsin() {
        return signInstrumentIsin;
    }

    public SendOrderDTO setSignInstrumentIsin(String signInstrumentIsin) {
        this.signInstrumentIsin = signInstrumentIsin;
        return this;
    }
}
