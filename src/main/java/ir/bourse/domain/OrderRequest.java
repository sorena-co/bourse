package ir.bourse.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A OrderRequest.
 */
@Entity
@Table(name = "order_request")
public class OrderRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "customer_id")
    private Long customerId;

    @Column(name = "customer_title")
    private String customerTitle;

    @Column(name = "order_side")
    private String orderSide;

    @Column(name = "order_side_id")
    private Long orderSideId;

    @Column(name = "price")
    private Long price;

    @Column(name = "quantity")
    private Long quantity;

    @Column(name = "value")
    private Long value;

    @Column(name = "validity_date")
    private String validityDate;

    @Column(name = "minimum_quantity")
    private Long minimumQuantity;

    @Column(name = "disclosed_quantity")
    private Long disclosedQuantity;

    @Column(name = "validity_type")
    private Long validityType;

    @Column(name = "bank_account_id")
    private Long bankAccountId;

    @Column(name = "expected_remaining_quantity")
    private Long expectedRemainingQuantity;

    @Column(name = "traded_quantity")
    private Long tradedQuantity;

    @Column(name = "category_id")
    private String categoryId;

    @Column(name = "remaining_quantity")
    private Long remainingQuantity;

    @Column(name = "order_executer_id")
    private Long orderExecuterId;

    @ManyToOne
    @JsonIgnoreProperties("orderRequests")
    private Sign sign;

    @ManyToOne
    @JsonIgnoreProperties("orderRequests")
    private UserAccount userAccount;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public OrderRequest customerId(Long customerId) {
        this.customerId = customerId;
        return this;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getCustomerTitle() {
        return customerTitle;
    }

    public OrderRequest customerTitle(String customerTitle) {
        this.customerTitle = customerTitle;
        return this;
    }

    public void setCustomerTitle(String customerTitle) {
        this.customerTitle = customerTitle;
    }

    public String getOrderSide() {
        return orderSide;
    }

    public OrderRequest orderSide(String orderSide) {
        this.orderSide = orderSide;
        return this;
    }

    public void setOrderSide(String orderSide) {
        this.orderSide = orderSide;
    }

    public Long getOrderSideId() {
        return orderSideId;
    }

    public OrderRequest orderSideId(Long orderSideId) {
        this.orderSideId = orderSideId;
        return this;
    }

    public void setOrderSideId(Long orderSideId) {
        this.orderSideId = orderSideId;
    }

    public Long getPrice() {
        return price;
    }

    public OrderRequest price(Long price) {
        this.price = price;
        return this;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Long getQuantity() {
        return quantity;
    }

    public OrderRequest quantity(Long quantity) {
        this.quantity = quantity;
        return this;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Long getValue() {
        return value;
    }

    public OrderRequest value(Long value) {
        this.value = value;
        return this;
    }

    public void setValue(Long value) {
        this.value = value;
    }

    public String getValidityDate() {
        return validityDate;
    }

    public OrderRequest validityDate(String validityDate) {
        this.validityDate = validityDate;
        return this;
    }

    public void setValidityDate(String validityDate) {
        this.validityDate = validityDate;
    }

    public Long getMinimumQuantity() {
        return minimumQuantity;
    }

    public OrderRequest minimumQuantity(Long minimumQuantity) {
        this.minimumQuantity = minimumQuantity;
        return this;
    }

    public void setMinimumQuantity(Long minimumQuantity) {
        this.minimumQuantity = minimumQuantity;
    }

    public Long getDisclosedQuantity() {
        return disclosedQuantity;
    }

    public OrderRequest disclosedQuantity(Long disclosedQuantity) {
        this.disclosedQuantity = disclosedQuantity;
        return this;
    }

    public void setDisclosedQuantity(Long disclosedQuantity) {
        this.disclosedQuantity = disclosedQuantity;
    }

    public Long getValidityType() {
        return validityType;
    }

    public OrderRequest validityType(Long validityType) {
        this.validityType = validityType;
        return this;
    }

    public void setValidityType(Long validityType) {
        this.validityType = validityType;
    }

    public Long getBankAccountId() {
        return bankAccountId;
    }

    public OrderRequest bankAccountId(Long bankAccountId) {
        this.bankAccountId = bankAccountId;
        return this;
    }

    public void setBankAccountId(Long bankAccountId) {
        this.bankAccountId = bankAccountId;
    }

    public Long getExpectedRemainingQuantity() {
        return expectedRemainingQuantity;
    }

    public OrderRequest expectedRemainingQuantity(Long expectedRemainingQuantity) {
        this.expectedRemainingQuantity = expectedRemainingQuantity;
        return this;
    }

    public void setExpectedRemainingQuantity(Long expectedRemainingQuantity) {
        this.expectedRemainingQuantity = expectedRemainingQuantity;
    }

    public Long getTradedQuantity() {
        return tradedQuantity;
    }

    public OrderRequest tradedQuantity(Long tradedQuantity) {
        this.tradedQuantity = tradedQuantity;
        return this;
    }

    public void setTradedQuantity(Long tradedQuantity) {
        this.tradedQuantity = tradedQuantity;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public OrderRequest categoryId(String categoryId) {
        this.categoryId = categoryId;
        return this;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public Long getRemainingQuantity() {
        return remainingQuantity;
    }

    public OrderRequest remainingQuantity(Long remainingQuantity) {
        this.remainingQuantity = remainingQuantity;
        return this;
    }

    public void setRemainingQuantity(Long remainingQuantity) {
        this.remainingQuantity = remainingQuantity;
    }

    public Long getOrderExecuterId() {
        return orderExecuterId;
    }

    public OrderRequest orderExecuterId(Long orderExecuterId) {
        this.orderExecuterId = orderExecuterId;
        return this;
    }

    public void setOrderExecuterId(Long orderExecuterId) {
        this.orderExecuterId = orderExecuterId;
    }

    public Sign getSign() {
        return sign;
    }

    public OrderRequest sign(Sign sign) {
        this.sign = sign;
        return this;
    }

    public void setSign(Sign sign) {
        this.sign = sign;
    }

    public UserAccount getUserAccount() {
        return userAccount;
    }

    public OrderRequest userAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
        return this;
    }

    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OrderRequest)) {
            return false;
        }
        return id != null && id.equals(((OrderRequest) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "OrderRequest{" +
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
            "}";
    }
}
