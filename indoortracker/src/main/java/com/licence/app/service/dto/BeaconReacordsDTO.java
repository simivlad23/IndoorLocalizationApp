package com.licence.app.service.dto;

import java.time.LocalDate;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.licence.app.domain.BeaconReacords} entity.
 */
public class BeaconReacordsDTO implements Serializable {
    
    private Long id;

    private LocalDate timeReacord;

    private Double readRssi;

    private Double meanRssi;

    private Double smootRssi;

    private Double smootDistance;

    private Double meanDistance;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getTimeReacord() {
        return timeReacord;
    }

    public void setTimeReacord(LocalDate timeReacord) {
        this.timeReacord = timeReacord;
    }

    public Double getReadRssi() {
        return readRssi;
    }

    public void setReadRssi(Double readRssi) {
        this.readRssi = readRssi;
    }

    public Double getMeanRssi() {
        return meanRssi;
    }

    public void setMeanRssi(Double meanRssi) {
        this.meanRssi = meanRssi;
    }

    public Double getSmootRssi() {
        return smootRssi;
    }

    public void setSmootRssi(Double smootRssi) {
        this.smootRssi = smootRssi;
    }

    public Double getSmootDistance() {
        return smootDistance;
    }

    public void setSmootDistance(Double smootDistance) {
        this.smootDistance = smootDistance;
    }

    public Double getMeanDistance() {
        return meanDistance;
    }

    public void setMeanDistance(Double meanDistance) {
        this.meanDistance = meanDistance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        BeaconReacordsDTO beaconReacordsDTO = (BeaconReacordsDTO) o;
        if (beaconReacordsDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), beaconReacordsDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "BeaconReacordsDTO{" +
            "id=" + getId() +
            ", timeReacord='" + getTimeReacord() + "'" +
            ", readRssi=" + getReadRssi() +
            ", meanRssi=" + getMeanRssi() +
            ", smootRssi=" + getSmootRssi() +
            ", smootDistance=" + getSmootDistance() +
            ", meanDistance=" + getMeanDistance() +
            "}";
    }
}
