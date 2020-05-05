package com.licence.app.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.licence.app.web.rest.TestUtil;

public class BeaconDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(BeaconDTO.class);
        BeaconDTO beaconDTO1 = new BeaconDTO();
        beaconDTO1.setId(1L);
        BeaconDTO beaconDTO2 = new BeaconDTO();
        assertThat(beaconDTO1).isNotEqualTo(beaconDTO2);
        beaconDTO2.setId(beaconDTO1.getId());
        assertThat(beaconDTO1).isEqualTo(beaconDTO2);
        beaconDTO2.setId(2L);
        assertThat(beaconDTO1).isNotEqualTo(beaconDTO2);
        beaconDTO1.setId(null);
        assertThat(beaconDTO1).isNotEqualTo(beaconDTO2);
    }
}
