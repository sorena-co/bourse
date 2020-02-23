package ir.bourse.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import ir.bourse.web.rest.TestUtil;

public class OrderRequestDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(OrderRequestDTO.class);
        OrderRequestDTO orderRequestDTO1 = new OrderRequestDTO();
        orderRequestDTO1.setId(1L);
        OrderRequestDTO orderRequestDTO2 = new OrderRequestDTO();
        assertThat(orderRequestDTO1).isNotEqualTo(orderRequestDTO2);
        orderRequestDTO2.setId(orderRequestDTO1.getId());
        assertThat(orderRequestDTO1).isEqualTo(orderRequestDTO2);
        orderRequestDTO2.setId(2L);
        assertThat(orderRequestDTO1).isNotEqualTo(orderRequestDTO2);
        orderRequestDTO1.setId(null);
        assertThat(orderRequestDTO1).isNotEqualTo(orderRequestDTO2);
    }
}
