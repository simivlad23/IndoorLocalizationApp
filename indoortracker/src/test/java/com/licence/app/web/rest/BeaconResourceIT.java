package com.licence.app.web.rest;

import com.licence.app.IndoortrackerApp;
import com.licence.app.domain.Beacon;
import com.licence.app.repository.BeaconRepository;
import com.licence.app.service.BeaconService;
import com.licence.app.service.dto.BeaconDTO;
import com.licence.app.service.mapper.BeaconMapper;

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
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link BeaconResource} REST controller.
 */
@SpringBootTest(classes = IndoortrackerApp.class)

@AutoConfigureMockMvc
@WithMockUser
public class BeaconResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESS = "BBBBBBBBBB";

    private static final Double DEFAULT_TX_POWER = 1D;
    private static final Double UPDATED_TX_POWER = 2D;

    private static final Integer DEFAULT_ADVERTISTING_INTERVAL = 1;
    private static final Integer UPDATED_ADVERTISTING_INTERVAL = 2;

    @Autowired
    private BeaconRepository beaconRepository;

    @Autowired
    private BeaconMapper beaconMapper;

    @Autowired
    private BeaconService beaconService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restBeaconMockMvc;

    private Beacon beacon;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Beacon createEntity(EntityManager em) {
        Beacon beacon = new Beacon()
            .name(DEFAULT_NAME)
            .address(DEFAULT_ADDRESS)
            .txPower(DEFAULT_TX_POWER)
            .advertistingInterval(DEFAULT_ADVERTISTING_INTERVAL);
        return beacon;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Beacon createUpdatedEntity(EntityManager em) {
        Beacon beacon = new Beacon()
            .name(UPDATED_NAME)
            .address(UPDATED_ADDRESS)
            .txPower(UPDATED_TX_POWER)
            .advertistingInterval(UPDATED_ADVERTISTING_INTERVAL);
        return beacon;
    }

    @BeforeEach
    public void initTest() {
        beacon = createEntity(em);
    }

    @Test
    @Transactional
    public void createBeacon() throws Exception {
        int databaseSizeBeforeCreate = beaconRepository.findAll().size();

        // Create the Beacon
        BeaconDTO beaconDTO = beaconMapper.toDto(beacon);
        restBeaconMockMvc.perform(post("/api/beacons")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(beaconDTO)))
            .andExpect(status().isCreated());

        // Validate the Beacon in the database
        List<Beacon> beaconList = beaconRepository.findAll();
        assertThat(beaconList).hasSize(databaseSizeBeforeCreate + 1);
        Beacon testBeacon = beaconList.get(beaconList.size() - 1);
        assertThat(testBeacon.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testBeacon.getAddress()).isEqualTo(DEFAULT_ADDRESS);
        assertThat(testBeacon.getTxPower()).isEqualTo(DEFAULT_TX_POWER);
        assertThat(testBeacon.getAdvertistingInterval()).isEqualTo(DEFAULT_ADVERTISTING_INTERVAL);
    }

    @Test
    @Transactional
    public void createBeaconWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = beaconRepository.findAll().size();

        // Create the Beacon with an existing ID
        beacon.setId(1L);
        BeaconDTO beaconDTO = beaconMapper.toDto(beacon);

        // An entity with an existing ID cannot be created, so this API call must fail
        restBeaconMockMvc.perform(post("/api/beacons")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(beaconDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Beacon in the database
        List<Beacon> beaconList = beaconRepository.findAll();
        assertThat(beaconList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllBeacons() throws Exception {
        // Initialize the database
        beaconRepository.saveAndFlush(beacon);

        // Get all the beaconList
        restBeaconMockMvc.perform(get("/api/beacons?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(beacon.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].address").value(hasItem(DEFAULT_ADDRESS)))
            .andExpect(jsonPath("$.[*].txPower").value(hasItem(DEFAULT_TX_POWER.doubleValue())))
            .andExpect(jsonPath("$.[*].advertistingInterval").value(hasItem(DEFAULT_ADVERTISTING_INTERVAL)));
    }
    
    @Test
    @Transactional
    public void getBeacon() throws Exception {
        // Initialize the database
        beaconRepository.saveAndFlush(beacon);

        // Get the beacon
        restBeaconMockMvc.perform(get("/api/beacons/{id}", beacon.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(beacon.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.address").value(DEFAULT_ADDRESS))
            .andExpect(jsonPath("$.txPower").value(DEFAULT_TX_POWER.doubleValue()))
            .andExpect(jsonPath("$.advertistingInterval").value(DEFAULT_ADVERTISTING_INTERVAL));
    }

    @Test
    @Transactional
    public void getNonExistingBeacon() throws Exception {
        // Get the beacon
        restBeaconMockMvc.perform(get("/api/beacons/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateBeacon() throws Exception {
        // Initialize the database
        beaconRepository.saveAndFlush(beacon);

        int databaseSizeBeforeUpdate = beaconRepository.findAll().size();

        // Update the beacon
        Beacon updatedBeacon = beaconRepository.findById(beacon.getId()).get();
        // Disconnect from session so that the updates on updatedBeacon are not directly saved in db
        em.detach(updatedBeacon);
        updatedBeacon
            .name(UPDATED_NAME)
            .address(UPDATED_ADDRESS)
            .txPower(UPDATED_TX_POWER)
            .advertistingInterval(UPDATED_ADVERTISTING_INTERVAL);
        BeaconDTO beaconDTO = beaconMapper.toDto(updatedBeacon);

        restBeaconMockMvc.perform(put("/api/beacons")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(beaconDTO)))
            .andExpect(status().isOk());

        // Validate the Beacon in the database
        List<Beacon> beaconList = beaconRepository.findAll();
        assertThat(beaconList).hasSize(databaseSizeBeforeUpdate);
        Beacon testBeacon = beaconList.get(beaconList.size() - 1);
        assertThat(testBeacon.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testBeacon.getAddress()).isEqualTo(UPDATED_ADDRESS);
        assertThat(testBeacon.getTxPower()).isEqualTo(UPDATED_TX_POWER);
        assertThat(testBeacon.getAdvertistingInterval()).isEqualTo(UPDATED_ADVERTISTING_INTERVAL);
    }

    @Test
    @Transactional
    public void updateNonExistingBeacon() throws Exception {
        int databaseSizeBeforeUpdate = beaconRepository.findAll().size();

        // Create the Beacon
        BeaconDTO beaconDTO = beaconMapper.toDto(beacon);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBeaconMockMvc.perform(put("/api/beacons")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(beaconDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Beacon in the database
        List<Beacon> beaconList = beaconRepository.findAll();
        assertThat(beaconList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteBeacon() throws Exception {
        // Initialize the database
        beaconRepository.saveAndFlush(beacon);

        int databaseSizeBeforeDelete = beaconRepository.findAll().size();

        // Delete the beacon
        restBeaconMockMvc.perform(delete("/api/beacons/{id}", beacon.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Beacon> beaconList = beaconRepository.findAll();
        assertThat(beaconList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
