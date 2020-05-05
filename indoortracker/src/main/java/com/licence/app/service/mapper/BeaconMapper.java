package com.licence.app.service.mapper;


import com.licence.app.domain.*;
import com.licence.app.service.dto.BeaconDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Beacon} and its DTO {@link BeaconDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface BeaconMapper extends EntityMapper<BeaconDTO, Beacon> {



    default Beacon fromId(Long id) {
        if (id == null) {
            return null;
        }
        Beacon beacon = new Beacon();
        beacon.setId(id);
        return beacon;
    }
}
