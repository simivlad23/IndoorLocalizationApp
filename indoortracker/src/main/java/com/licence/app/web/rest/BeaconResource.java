package com.licence.app.web.rest;

import com.licence.app.service.BeaconService;
import com.licence.app.web.rest.errors.BadRequestAlertException;
import com.licence.app.service.dto.BeaconDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.licence.app.domain.Beacon}.
 */
@RestController
@RequestMapping("/api")
public class BeaconResource {

    private final Logger log = LoggerFactory.getLogger(BeaconResource.class);

    private static final String ENTITY_NAME = "indoortrackerBeacon";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BeaconService beaconService;

    public BeaconResource(BeaconService beaconService) {
        this.beaconService = beaconService;
    }

    /**
     * {@code POST  /beacons} : Create a new beacon.
     *
     * @param beaconDTO the beaconDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new beaconDTO, or with status {@code 400 (Bad Request)} if the beacon has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/beacons")
    public ResponseEntity<BeaconDTO> createBeacon(@RequestBody BeaconDTO beaconDTO) throws URISyntaxException {
        log.debug("REST request to save Beacon : {}", beaconDTO);
        if (beaconDTO.getId() != null) {
            throw new BadRequestAlertException("A new beacon cannot already have an ID", ENTITY_NAME, "idexists");
        }
        BeaconDTO result = beaconService.save(beaconDTO);
        return ResponseEntity.created(new URI("/api/beacons/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /beacons} : Updates an existing beacon.
     *
     * @param beaconDTO the beaconDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated beaconDTO,
     * or with status {@code 400 (Bad Request)} if the beaconDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the beaconDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/beacons")
    public ResponseEntity<BeaconDTO> updateBeacon(@RequestBody BeaconDTO beaconDTO) throws URISyntaxException {
        log.debug("REST request to update Beacon : {}", beaconDTO);
        if (beaconDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        BeaconDTO result = beaconService.save(beaconDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, beaconDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /beacons} : get all the beacons.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of beacons in body.
     */
    @GetMapping("/beacons")
    public ResponseEntity<List<BeaconDTO>> getAllBeacons(Pageable pageable) {
        log.debug("REST request to get a page of Beacons");
        Page<BeaconDTO> page = beaconService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /beacons/:id} : get the "id" beacon.
     *
     * @param id the id of the beaconDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the beaconDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/beacons/{id}")
    public ResponseEntity<BeaconDTO> getBeacon(@PathVariable Long id) {
        log.debug("REST request to get Beacon : {}", id);
        Optional<BeaconDTO> beaconDTO = beaconService.findOne(id);
        return ResponseUtil.wrapOrNotFound(beaconDTO);
    }

    /**
     * {@code DELETE  /beacons/:id} : delete the "id" beacon.
     *
     * @param id the id of the beaconDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/beacons/{id}")
    public ResponseEntity<Void> deleteBeacon(@PathVariable Long id) {
        log.debug("REST request to delete Beacon : {}", id);
        beaconService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
