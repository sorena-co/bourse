package ir.bourse.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class OrderRequestMapperTest {

    private OrderRequestMapper orderRequestMapper;

    @BeforeEach
    public void setUp() {
        orderRequestMapper = new OrderRequestMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(orderRequestMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(orderRequestMapper.fromId(null)).isNull();
    }
}
