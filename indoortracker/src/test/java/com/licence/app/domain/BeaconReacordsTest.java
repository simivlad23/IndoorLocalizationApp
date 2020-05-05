package com.licence.app.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.licence.app.web.rest.TestUtil;

public class BeaconReacordsTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(BeaconReacords.class);
        BeaconReacords beaconReacords1 = new BeaconReacords();
        beaconReacords1.setId(1L);
        BeaconReacords beaconReacords2 = new BeaconReacords();
        beaconReacords2.setId(beaconReacords1.getId());
        assertThat(beaconReacords1).isEqualTo(beaconReacords2);
        beaconReacords2.setId(2L);
        assertThat(beaconReacords1).isNotEqualTo(beaconReacords2);
        beaconReacords1.setId(null);
        assertThat(beaconReacords1).isNotEqualTo(beaconReacords2);
    }
}
