package com.licence.app.service;

import com.licence.app.service.dto.BeaconDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.licence.app.domain.Beacon}.
 */
public interface BeaconService {

    /**
     * Save a beacon.
     *
     * @param beaconDTO the entity to save.
     * @return the persisted entity.
     */
    BeaconDTO save(BeaconDTO beaconDTO);

    /**
     * Get all the beacons.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<BeaconDTO> findAll(Pageable pageable);

    /**
     * Get the "id" beacon.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<BeaconDTO> findOne(Long id);

    /**
     * Delete the "id" beacon.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
