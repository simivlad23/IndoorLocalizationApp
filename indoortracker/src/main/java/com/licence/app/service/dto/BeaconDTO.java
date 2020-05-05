package com.licence.app.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.licence.app.domain.Beacon} entity.
 */
public class BeaconDTO implements Serializable {
    
    private Long id;

    private String name;

    private String address;

    private Double txPower;

    private Integer advertistingInterval;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getTxPower() {
        return txPower;
    }

    public void setTxPower(Double txPower) {
        this.txPower = txPower;
    }

    public Integer getAdvertistingInterval() {
        return advertistingInterval;
    }

    public void setAdvertistingInterval(Integer advertistingInterval) {
        this.advertistingInterval = advertistingInterval;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        BeaconDTO beaconDTO = (BeaconDTO) o;
        if (beaconDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), beaconDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "BeaconDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", address='" + getAddress() + "'" +
            ", txPower=" + getTxPower() +
            ", advertistingInterval=" + getAdvertistingInterval() +
            "}";
    }
}
