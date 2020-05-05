package com.licence.app.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Beacon.
 */
@Entity
@Table(name = "beacon")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Beacon implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @Column(name = "tx_power")
    private Double txPower;

    @Column(name = "advertisting_interval")
    private Integer advertistingInterval;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Beacon name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public Beacon address(String address) {
        this.address = address;
        return this;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getTxPower() {
        return txPower;
    }

    public Beacon txPower(Double txPower) {
        this.txPower = txPower;
        return this;
    }

    public void setTxPower(Double txPower) {
        this.txPower = txPower;
    }

    public Integer getAdvertistingInterval() {
        return advertistingInterval;
    }

    public Beacon advertistingInterval(Integer advertistingInterval) {
        this.advertistingInterval = advertistingInterval;
        return this;
    }

    public void setAdvertistingInterval(Integer advertistingInterval) {
        this.advertistingInterval = advertistingInterval;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Beacon)) {
            return false;
        }
        return id != null && id.equals(((Beacon) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Beacon{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", address='" + getAddress() + "'" +
            ", txPower=" + getTxPower() +
            ", advertistingInterval=" + getAdvertistingInterval() +
            "}";
    }
}
