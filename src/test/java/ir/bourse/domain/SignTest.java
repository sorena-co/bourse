package ir.bourse.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import ir.bourse.web.rest.TestUtil;

public class SignTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Sign.class);
        Sign sign1 = new Sign();
        sign1.setId(1L);
        Sign sign2 = new Sign();
        sign2.setId(sign1.getId());
        assertThat(sign1).isEqualTo(sign2);
        sign2.setId(2L);
        assertThat(sign1).isNotEqualTo(sign2);
        sign1.setId(null);
        assertThat(sign1).isNotEqualTo(sign2);
    }
}
