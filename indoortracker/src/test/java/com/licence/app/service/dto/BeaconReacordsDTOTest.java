package com.licence.app.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.licence.app.web.rest.TestUtil;

public class BeaconReacordsDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(BeaconReacordsDTO.class);
        BeaconReacordsDTO beaconReacordsDTO1 = new BeaconReacordsDTO();
        beaconReacordsDTO1.setId(1L);
        BeaconReacordsDTO beaconReacordsDTO2 = new BeaconReacordsDTO();
        assertThat(beaconReacordsDTO1).isNotEqualTo(beaconReacordsDTO2);
        beaconReacordsDTO2.setId(beaconReacordsDTO1.getId());
        assertThat(beaconReacordsDTO1).isEqualTo(beaconReacordsDTO2);
        beaconReacordsDTO2.setId(2L);
        assertThat(beaconReacordsDTO1).isNotEqualTo(beaconReacordsDTO2);
        beaconReacordsDTO1.setId(null);
        assertThat(beaconReacordsDTO1).isNotEqualTo(beaconReacordsDTO2);
    }
}
