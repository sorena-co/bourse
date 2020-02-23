package ir.bourse.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import ir.bourse.web.rest.TestUtil;

public class OrderRequestTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(OrderRequest.class);
        OrderRequest orderRequest1 = new OrderRequest();
        orderRequest1.setId(1L);
        OrderRequest orderRequest2 = new OrderRequest();
        orderRequest2.setId(orderRequest1.getId());
        assertThat(orderRequest1).isEqualTo(orderRequest2);
        orderRequest2.setId(2L);
        assertThat(orderRequest1).isNotEqualTo(orderRequest2);
        orderRequest1.setId(null);
        assertThat(orderRequest1).isNotEqualTo(orderRequest2);
    }
}
