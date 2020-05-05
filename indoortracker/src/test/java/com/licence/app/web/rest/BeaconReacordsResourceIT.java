package com.licence.app.web.rest;

import com.licence.app.IndoortrackerApp;
import com.licence.app.domain.BeaconReacords;
import com.licence.app.repository.BeaconReacordsRepository;
import com.licence.app.service.BeaconReacordsService;
import com.licence.app.service.dto.BeaconReacordsDTO;
import com.licence.app.service.mapper.BeaconReacordsMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link BeaconReacordsResource} REST controller.
 */
@SpringBootTest(classes = IndoortrackerApp.class)

@AutoConfigureMockMvc
@WithMockUser
public class BeaconReacordsResourceIT {

    private static final LocalDate DEFAULT_TIME_REACORD = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_TIME_REACORD = LocalDate.now(ZoneId.systemDefault());

    private static final Double DEFAULT_READ_RSSI = 1D;
    private static final Double UPDATED_READ_RSSI = 2D;

    private static final Double DEFAULT_MEAN_RSSI = 1D;
    private static final Double UPDATED_MEAN_RSSI = 2D;

    private static final Double DEFAULT_SMOOT_RSSI = 1D;
    private static final Double UPDATED_SMOOT_RSSI = 2D;

    private static final Double DEFAULT_SMOOT_DISTANCE = 1D;
    private static final Double UPDATED_SMOOT_DISTANCE = 2D;

    private static final Double DEFAULT_MEAN_DISTANCE = 1D;
    private static final Double UPDATED_MEAN_DISTANCE = 2D;

    @Autowired
    private BeaconReacordsRepository beaconReacordsRepository;

    @Autowired
    private BeaconReacordsMapper beaconReacordsMapper;

    @Autowired
    private BeaconReacordsService beaconReacordsService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restBeaconReacordsMockMvc;

    private BeaconReacords beaconReacords;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BeaconReacords createEntity(EntityManager em) {
        BeaconReacords beaconReacords = new BeaconReacords()
            .timeReacord(DEFAULT_TIME_REACORD)
            .readRssi(DEFAULT_READ_RSSI)
            .meanRssi(DEFAULT_MEAN_RSSI)
            .smootRssi(DEFAULT_SMOOT_RSSI)
            .smootDistance(DEFAULT_SMOOT_DISTANCE)
            .meanDistance(DEFAULT_MEAN_DISTANCE);
        return beaconReacords;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BeaconReacords createUpdatedEntity(EntityManager em) {
        BeaconReacords beaconReacords = new BeaconReacords()
            .timeReacord(UPDATED_TIME_REACORD)
            .readRssi(UPDATED_READ_RSSI)
            .meanRssi(UPDATED_MEAN_RSSI)
            .smootRssi(UPDATED_SMOOT_RSSI)
            .smootDistance(UPDATED_SMOOT_DISTANCE)
            .meanDistance(UPDATED_MEAN_DISTANCE);
        return beaconReacords;
    }

    @BeforeEach
    public void initTest() {
        beaconReacords = createEntity(em);
    }

    @Test
    @Transactional
    public void createBeaconReacords() throws Exception {
        int databaseSizeBeforeCreate = beaconReacordsRepository.findAll().size();

        // Create the BeaconReacords
        BeaconReacordsDTO beaconReacordsDTO = beaconReacordsMapper.toDto(beaconReacords);
        restBeaconReacordsMockMvc.perform(post("/api/beacon-reacords")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(beaconReacordsDTO)))
            .andExpect(status().isCreated());

        // Validate the BeaconReacords in the database
        List<BeaconReacords> beaconReacordsList = beaconReacordsRepository.findAll();
        assertThat(beaconReacordsList).hasSize(databaseSizeBeforeCreate + 1);
        BeaconReacords testBeaconReacords = beaconReacordsList.get(beaconReacordsList.size() - 1);
        assertThat(testBeaconReacords.getTimeReacord()).isEqualTo(DEFAULT_TIME_REACORD);
        assertThat(testBeaconReacords.getReadRssi()).isEqualTo(DEFAULT_READ_RSSI);
        assertThat(testBeaconReacords.getMeanRssi()).isEqualTo(DEFAULT_MEAN_RSSI);
        assertThat(testBeaconReacords.getSmootRssi()).isEqualTo(DEFAULT_SMOOT_RSSI);
        assertThat(testBeaconReacords.getSmootDistance()).isEqualTo(DEFAULT_SMOOT_DISTANCE);
        assertThat(testBeaconReacords.getMeanDistance()).isEqualTo(DEFAULT_MEAN_DISTANCE);
    }

    @Test
    @Transactional
    public void createBeaconReacordsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = beaconReacordsRepository.findAll().size();

        // Create the BeaconReacords with an existing ID
        beaconReacords.setId(1L);
        BeaconReacordsDTO beaconReacordsDTO = beaconReacordsMapper.toDto(beaconReacords);

        // An entity with an existing ID cannot be created, so this API call must fail
        restBeaconReacordsMockMvc.perform(post("/api/beacon-reacords")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(beaconReacordsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the BeaconReacords in the database
        List<BeaconReacords> beaconReacordsList = beaconReacordsRepository.findAll();
        assertThat(beaconReacordsList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllBeaconReacords() throws Exception {
        // Initialize the database
        beaconReacordsRepository.saveAndFlush(beaconReacords);

        // Get all the beaconReacordsList
        restBeaconReacordsMockMvc.perform(get("/api/beacon-reacords?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(beaconReacords.getId().intValue())))
            .andExpect(jsonPath("$.[*].timeReacord").value(hasItem(DEFAULT_TIME_REACORD.toString())))
            .andExpect(jsonPath("$.[*].readRssi").value(hasItem(DEFAULT_READ_RSSI.doubleValue())))
            .andExpect(jsonPath("$.[*].meanRssi").value(hasItem(DEFAULT_MEAN_RSSI.doubleValue())))
            .andExpect(jsonPath("$.[*].smootRssi").value(hasItem(DEFAULT_SMOOT_RSSI.doubleValue())))
            .andExpect(jsonPath("$.[*].smootDistance").value(hasItem(DEFAULT_SMOOT_DISTANCE.doubleValue())))
            .andExpect(jsonPath("$.[*].meanDistance").value(hasItem(DEFAULT_MEAN_DISTANCE.doubleValue())));
    }
    
    @Test
    @Transactional
    public void getBeaconReacords() throws Exception {
        // Initialize the database
        beaconReacordsRepository.saveAndFlush(beaconReacords);

        // Get the beaconReacords
        restBeaconReacordsMockMvc.perform(get("/api/beacon-reacords/{id}", beaconReacords.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(beaconReacords.getId().intValue()))
            .andExpect(jsonPath("$.timeReacord").value(DEFAULT_TIME_REACORD.toString()))
            .andExpect(jsonPath("$.readRssi").value(DEFAULT_READ_RSSI.doubleValue()))
            .andExpect(jsonPath("$.meanRssi").value(DEFAULT_MEAN_RSSI.doubleValue()))
            .andExpect(jsonPath("$.smootRssi").value(DEFAULT_SMOOT_RSSI.doubleValue()))
            .andExpect(jsonPath("$.smootDistance").value(DEFAULT_SMOOT_DISTANCE.doubleValue()))
            .andExpect(jsonPath("$.meanDistance").value(DEFAULT_MEAN_DISTANCE.doubleValue()));
    }

    @Test
    @Transactional
    public void getNonExistingBeaconReacords() throws Exception {
        // Get the beaconReacords
        restBeaconReacordsMockMvc.perform(get("/api/beacon-reacords/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateBeaconReacords() throws Exception {
        // Initialize the database
        beaconReacordsRepository.saveAndFlush(beaconReacords);

        int databaseSizeBeforeUpdate = beaconReacordsRepository.findAll().size();

        // Update the beaconReacords
        BeaconReacords updatedBeaconReacords = beaconReacordsRepository.findById(beaconReacords.getId()).get();
        // Disconnect from session so that the updates on updatedBeaconReacords are not directly saved in db
        em.detach(updatedBeaconReacords);
        updatedBeaconReacords
            .timeReacord(UPDATED_TIME_REACORD)
            .readRssi(UPDATED_READ_RSSI)
            .meanRssi(UPDATED_MEAN_RSSI)
            .smootRssi(UPDATED_SMOOT_RSSI)
            .smootDistance(UPDATED_SMOOT_DISTANCE)
            .meanDistance(UPDATED_MEAN_DISTANCE);
        BeaconReacordsDTO beaconReacordsDTO = beaconReacordsMapper.toDto(updatedBeaconReacords);

        restBeaconReacordsMockMvc.perform(put("/api/beacon-reacords")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(beaconReacordsDTO)))
            .andExpect(status().isOk());

        // Validate the BeaconReacords in the database
        List<BeaconReacords> beaconReacordsList = beaconReacordsRepository.findAll();
        assertThat(beaconReacordsList).hasSize(databaseSizeBeforeUpdate);
        BeaconReacords testBeaconReacords = beaconReacordsList.get(beaconReacordsList.size() - 1);
        assertThat(testBeaconReacords.getTimeReacord()).isEqualTo(UPDATED_TIME_REACORD);
        assertThat(testBeaconReacords.getReadRssi()).isEqualTo(UPDATED_READ_RSSI);
        assertThat(testBeaconReacords.getMeanRssi()).isEqualTo(UPDATED_MEAN_RSSI);
        assertThat(testBeaconReacords.getSmootRssi()).isEqualTo(UPDATED_SMOOT_RSSI);
        assertThat(testBeaconReacords.getSmootDistance()).isEqualTo(UPDATED_SMOOT_DISTANCE);
        assertThat(testBeaconReacords.getMeanDistance()).isEqualTo(UPDATED_MEAN_DISTANCE);
    }

    @Test
    @Transactional
    public void updateNonExistingBeaconReacords() throws Exception {
        int databaseSizeBeforeUpdate = beaconReacordsRepository.findAll().size();

        // Create the BeaconReacords
        BeaconReacordsDTO beaconReacordsDTO = beaconReacordsMapper.toDto(beaconReacords);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBeaconReacordsMockMvc.perform(put("/api/beacon-reacords")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(beaconReacordsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the BeaconReacords in the database
        List<BeaconReacords> beaconReacordsList = beaconReacordsRepository.findAll();
        assertThat(beaconReacordsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteBeaconReacords() throws Exception {
        // Initialize the database
        beaconReacordsRepository.saveAndFlush(beaconReacords);

        int databaseSizeBeforeDelete = beaconReacordsRepository.findAll().size();

        // Delete the beaconReacords
        restBeaconReacordsMockMvc.perform(delete("/api/beacon-reacords/{id}", beaconReacords.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<BeaconReacords> beaconReacordsList = beaconReacordsRepository.findAll();
        assertThat(beaconReacordsList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
