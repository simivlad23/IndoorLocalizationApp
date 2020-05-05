package com.licence.app.service.impl;

import com.licence.app.service.BeaconService;
import com.licence.app.domain.Beacon;
import com.licence.app.repository.BeaconRepository;
import com.licence.app.service.dto.BeaconDTO;
import com.licence.app.service.mapper.BeaconMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Beacon}.
 */
@Service
@Transactional
public class BeaconServiceImpl implements BeaconService {

    private final Logger log = LoggerFactory.getLogger(BeaconServiceImpl.class);

    private final BeaconRepository beaconRepository;

    private final BeaconMapper beaconMapper;

    public BeaconServiceImpl(BeaconRepository beaconRepository, BeaconMapper beaconMapper) {
        this.beaconRepository = beaconRepository;
        this.beaconMapper = beaconMapper;
    }

    /**
     * Save a beacon.
     *
     * @param beaconDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public BeaconDTO save(BeaconDTO beaconDTO) {
        log.debug("Request to save Beacon : {}", beaconDTO);
        Beacon beacon = beaconMapper.toEntity(beaconDTO);
        beacon = beaconRepository.save(beacon);
        return beaconMapper.toDto(beacon);
    }

    /**
     * Get all the beacons.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<BeaconDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Beacons");
        return beaconRepository.findAll(pageable)
            .map(beaconMapper::toDto);
    }

    /**
     * Get one beacon by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<BeaconDTO> findOne(Long id) {
        log.debug("Request to get Beacon : {}", id);
        return beaconRepository.findById(id)
            .map(beaconMapper::toDto);
    }

    /**
     * Delete the beacon by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Beacon : {}", id);
        beaconRepository.deleteById(id);
    }
}
