package com.licence.app.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class BeaconMapperTest {

    private BeaconMapper beaconMapper;

    @BeforeEach
    public void setUp() {
        beaconMapper = new BeaconMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(beaconMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(beaconMapper.fromId(null)).isNull();
    }
}
