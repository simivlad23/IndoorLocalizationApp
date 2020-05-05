package com.licence.app.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;
import java.time.LocalDate;

/**
 * A BeaconReacords.
 */
@Entity
@Table(name = "beacon_reacords")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class BeaconReacords implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "time_reacord")
    private LocalDate timeReacord;

    @Column(name = "read_rssi")
    private Double readRssi;

    @Column(name = "mean_rssi")
    private Double meanRssi;

    @Column(name = "smoot_rssi")
    private Double smootRssi;

    @Column(name = "smoot_distance")
    private Double smootDistance;

    @Column(name = "mean_distance")
    private Double meanDistance;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getTimeReacord() {
        return timeReacord;
    }

    public BeaconReacords timeReacord(LocalDate timeReacord) {
        this.timeReacord = timeReacord;
        return this;
    }

    public void setTimeReacord(LocalDate timeReacord) {
        this.timeReacord = timeReacord;
    }

    public Double getReadRssi() {
        return readRssi;
    }

    public BeaconReacords readRssi(Double readRssi) {
        this.readRssi = readRssi;
        return this;
    }

    public void setReadRssi(Double readRssi) {
        this.readRssi = readRssi;
    }

    public Double getMeanRssi() {
        return meanRssi;
    }

    public BeaconReacords meanRssi(Double meanRssi) {
        this.meanRssi = meanRssi;
        return this;
    }

    public void setMeanRssi(Double meanRssi) {
        this.meanRssi = meanRssi;
    }

    public Double getSmootRssi() {
        return smootRssi;
    }

    public BeaconReacords smootRssi(Double smootRssi) {
        this.smootRssi = smootRssi;
        return this;
    }

    public void setSmootRssi(Double smootRssi) {
        this.smootRssi = smootRssi;
    }

    public Double getSmootDistance() {
        return smootDistance;
    }

    public BeaconReacords smootDistance(Double smootDistance) {
        this.smootDistance = smootDistance;
        return this;
    }

    public void setSmootDistance(Double smootDistance) {
        this.smootDistance = smootDistance;
    }

    public Double getMeanDistance() {
        return meanDistance;
    }

    public BeaconReacords meanDistance(Double meanDistance) {
        this.meanDistance = meanDistance;
        return this;
    }

    public void setMeanDistance(Double meanDistance) {
        this.meanDistance = meanDistance;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BeaconReacords)) {
            return false;
        }
        return id != null && id.equals(((BeaconReacords) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "BeaconReacords{" +
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
