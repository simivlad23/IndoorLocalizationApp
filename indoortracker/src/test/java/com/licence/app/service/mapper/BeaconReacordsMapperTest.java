package com.licence.app.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class BeaconReacordsMapperTest {

    private BeaconReacordsMapper beaconReacordsMapper;

    @BeforeEach
    public void setUp() {
        beaconReacordsMapper = new BeaconReacordsMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(beaconReacordsMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(beaconReacordsMapper.fromId(null)).isNull();
    }
}
