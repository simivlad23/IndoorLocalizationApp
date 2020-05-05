package com.licence.app.service.impl;

import com.licence.app.service.BeaconReacordsService;
import com.licence.app.domain.BeaconReacords;
import com.licence.app.repository.BeaconReacordsRepository;
import com.licence.app.service.dto.BeaconReacordsDTO;
import com.licence.app.service.mapper.BeaconReacordsMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link BeaconReacords}.
 */
@Service
@Transactional
public class BeaconReacordsServiceImpl implements BeaconReacordsService {

    private final Logger log = LoggerFactory.getLogger(BeaconReacordsServiceImpl.class);

    private final BeaconReacordsRepository beaconReacordsRepository;

    private final BeaconReacordsMapper beaconReacordsMapper;

    public BeaconReacordsServiceImpl(BeaconReacordsRepository beaconReacordsRepository, BeaconReacordsMapper beaconReacordsMapper) {
        this.beaconReacordsRepository = beaconReacordsRepository;
        this.beaconReacordsMapper = beaconReacordsMapper;
    }

    /**
     * Save a beaconReacords.
     *
     * @param beaconReacordsDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public BeaconReacordsDTO save(BeaconReacordsDTO beaconReacordsDTO) {
        log.debug("Request to save BeaconReacords : {}", beaconReacordsDTO);
        BeaconReacords beaconReacords = beaconReacordsMapper.toEntity(beaconReacordsDTO);
        beaconReacords = beaconReacordsRepository.save(beaconReacords);
        return beaconReacordsMapper.toDto(beaconReacords);
    }

    /**
     * Get all the beaconReacords.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<BeaconReacordsDTO> findAll(Pageable pageable) {
        log.debug("Request to get all BeaconReacords");
        return beaconReacordsRepository.findAll(pageable)
            .map(beaconReacordsMapper::toDto);
    }

    /**
     * Get one beaconReacords by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<BeaconReacordsDTO> findOne(Long id) {
        log.debug("Request to get BeaconReacords : {}", id);
        return beaconReacordsRepository.findById(id)
            .map(beaconReacordsMapper::toDto);
    }

    /**
     * Delete the beaconReacords by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete BeaconReacords : {}", id);
        beaconReacordsRepository.deleteById(id);
    }
}
