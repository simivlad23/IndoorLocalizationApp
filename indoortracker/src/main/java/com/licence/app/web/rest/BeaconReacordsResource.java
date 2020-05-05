package com.licence.app.web.rest;

import com.licence.app.service.BeaconReacordsService;
import com.licence.app.web.rest.errors.BadRequestAlertException;
import com.licence.app.service.dto.BeaconReacordsDTO;

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
 * REST controller for managing {@link com.licence.app.domain.BeaconReacords}.
 */
@RestController
@RequestMapping("/api")
public class BeaconReacordsResource {

    private final Logger log = LoggerFactory.getLogger(BeaconReacordsResource.class);

    private static final String ENTITY_NAME = "indoortrackerBeaconReacords";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BeaconReacordsService beaconReacordsService;

    public BeaconReacordsResource(BeaconReacordsService beaconReacordsService) {
        this.beaconReacordsService = beaconReacordsService;
    }

    /**
     * {@code POST  /beacon-reacords} : Create a new beaconReacords.
     *
     * @param beaconReacordsDTO the beaconReacordsDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new beaconReacordsDTO, or with status {@code 400 (Bad Request)} if the beaconReacords has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/beacon-reacords")
    public ResponseEntity<BeaconReacordsDTO> createBeaconReacords(@RequestBody BeaconReacordsDTO beaconReacordsDTO) throws URISyntaxException {
        log.debug("REST request to save BeaconReacords : {}", beaconReacordsDTO);
        if (beaconReacordsDTO.getId() != null) {
            throw new BadRequestAlertException("A new beaconReacords cannot already have an ID", ENTITY_NAME, "idexists");
        }
        BeaconReacordsDTO result = beaconReacordsService.save(beaconReacordsDTO);
        return ResponseEntity.created(new URI("/api/beacon-reacords/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /beacon-reacords} : Updates an existing beaconReacords.
     *
     * @param beaconReacordsDTO the beaconReacordsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated beaconReacordsDTO,
     * or with status {@code 400 (Bad Request)} if the beaconReacordsDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the beaconReacordsDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/beacon-reacords")
    public ResponseEntity<BeaconReacordsDTO> updateBeaconReacords(@RequestBody BeaconReacordsDTO beaconReacordsDTO) throws URISyntaxException {
        log.debug("REST request to update BeaconReacords : {}", beaconReacordsDTO);
        if (beaconReacordsDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        BeaconReacordsDTO result = beaconReacordsService.save(beaconReacordsDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, beaconReacordsDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /beacon-reacords} : get all the beaconReacords.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of beaconReacords in body.
     */
    @GetMapping("/beacon-reacords")
    public ResponseEntity<List<BeaconReacordsDTO>> getAllBeaconReacords(Pageable pageable) {
        log.debug("REST request to get a page of BeaconReacords");
        Page<BeaconReacordsDTO> page = beaconReacordsService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /beacon-reacords/:id} : get the "id" beaconReacords.
     *
     * @param id the id of the beaconReacordsDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the beaconReacordsDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/beacon-reacords/{id}")
    public ResponseEntity<BeaconReacordsDTO> getBeaconReacords(@PathVariable Long id) {
        log.debug("REST request to get BeaconReacords : {}", id);
        Optional<BeaconReacordsDTO> beaconReacordsDTO = beaconReacordsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(beaconReacordsDTO);
    }

    /**
     * {@code DELETE  /beacon-reacords/:id} : delete the "id" beaconReacords.
     *
     * @param id the id of the beaconReacordsDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/beacon-reacords/{id}")
    public ResponseEntity<Void> deleteBeaconReacords(@PathVariable Long id) {
        log.debug("REST request to delete BeaconReacords : {}", id);
        beaconReacordsService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
