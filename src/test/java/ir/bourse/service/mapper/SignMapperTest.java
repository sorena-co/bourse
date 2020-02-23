package ir.bourse.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class SignMapperTest {

    private SignMapper signMapper;

    @BeforeEach
    public void setUp() {
        signMapper = new SignMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(signMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(signMapper.fromId(null)).isNull();
    }
}
