package ir.bourse.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link ir.bourse.domain.OrderRequest} entity.
 */
public class RequestDTO implements Serializable {
    private SendOrderDTO orderModel;
    private String nonce;

    public SendOrderDTO getOrderModel() {
        return orderModel;
    }

    public void setOrderModel(SendOrderDTO orderModel) {
        this.orderModel = orderModel;
    }

    public String getNonce() {
        return nonce;
    }

    public void setNonce(String nonce) {
        this.nonce = nonce;
    }
}
