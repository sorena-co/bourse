package ir.bourse.domain;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A Sign.
 */
@Entity
@Table(name = "sign")
public class Sign implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "instrument_id")
    private Long instrumentId;

    @Column(name = "instrument_isin")
    private String instrumentIsin;

    @Column(name = "instrument_name")
    private String instrumentName;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getInstrumentId() {
        return instrumentId;
    }

    public Sign instrumentId(Long instrumentId) {
        this.instrumentId = instrumentId;
        return this;
    }

    public void setInstrumentId(Long instrumentId) {
        this.instrumentId = instrumentId;
    }

    public String getInstrumentIsin() {
        return instrumentIsin;
    }

    public Sign instrumentIsin(String instrumentIsin) {
        this.instrumentIsin = instrumentIsin;
        return this;
    }

    public void setInstrumentIsin(String instrumentIsin) {
        this.instrumentIsin = instrumentIsin;
    }

    public String getInstrumentName() {
        return instrumentName;
    }

    public Sign instrumentName(String instrumentName) {
        this.instrumentName = instrumentName;
        return this;
    }

    public void setInstrumentName(String instrumentName) {
        this.instrumentName = instrumentName;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Sign)) {
            return false;
        }
        return id != null && id.equals(((Sign) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Sign{" +
            "id=" + getId() +
            ", instrumentId=" + getInstrumentId() +
            ", instrumentIsin='" + getInstrumentIsin() + "'" +
            ", instrumentName='" + getInstrumentName() + "'" +
            "}";
    }
}
