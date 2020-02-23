package ir.bourse.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import ir.bourse.web.rest.TestUtil;

public class SignDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(SignDTO.class);
        SignDTO signDTO1 = new SignDTO();
        signDTO1.setId(1L);
        SignDTO signDTO2 = new SignDTO();
        assertThat(signDTO1).isNotEqualTo(signDTO2);
        signDTO2.setId(signDTO1.getId());
        assertThat(signDTO1).isEqualTo(signDTO2);
        signDTO2.setId(2L);
        assertThat(signDTO1).isNotEqualTo(signDTO2);
        signDTO1.setId(null);
        assertThat(signDTO1).isNotEqualTo(signDTO2);
    }
}
