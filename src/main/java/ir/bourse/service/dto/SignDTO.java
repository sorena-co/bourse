package ir.bourse.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link ir.bourse.domain.Sign} entity.
 */
public class SignDTO implements Serializable {

    private Long id;

    private Long instrumentId;

    private String instrumentIsin;

    private String instrumentName;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getInstrumentId() {
        return instrumentId;
    }

    public void setInstrumentId(Long instrumentId) {
        this.instrumentId = instrumentId;
    }

    public String getInstrumentIsin() {
        return instrumentIsin;
    }

    public void setInstrumentIsin(String instrumentIsin) {
        this.instrumentIsin = instrumentIsin;
    }

    public String getInstrumentName() {
        return instrumentName;
    }

    public void setInstrumentName(String instrumentName) {
        this.instrumentName = instrumentName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SignDTO signDTO = (SignDTO) o;
        if (signDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), signDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SignDTO{" +
            "id=" + getId() +
            ", instrumentId=" + getInstrumentId() +
            ", instrumentIsin='" + getInstrumentIsin() + "'" +
            ", instrumentName='" + getInstrumentName() + "'" +
            "}";
    }
}
