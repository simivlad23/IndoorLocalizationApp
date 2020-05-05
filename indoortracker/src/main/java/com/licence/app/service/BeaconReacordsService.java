package com.licence.app.service;

import com.licence.app.service.dto.BeaconReacordsDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.licence.app.domain.BeaconReacords}.
 */
public interface BeaconReacordsService {

    /**
     * Save a beaconReacords.
     *
     * @param beaconReacordsDTO the entity to save.
     * @return the persisted entity.
     */
    BeaconReacordsDTO save(BeaconReacordsDTO beaconReacordsDTO);

    /**
     * Get all the beaconReacords.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<BeaconReacordsDTO> findAll(Pageable pageable);

    /**
     * Get the "id" beaconReacords.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<BeaconReacordsDTO> findOne(Long id);

    /**
     * Delete the "id" beaconReacords.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
