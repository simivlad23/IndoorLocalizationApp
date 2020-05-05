package com.licence.app.service.mapper;


import com.licence.app.domain.*;
import com.licence.app.service.dto.BeaconReacordsDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link BeaconReacords} and its DTO {@link BeaconReacordsDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface BeaconReacordsMapper extends EntityMapper<BeaconReacordsDTO, BeaconReacords> {



    default BeaconReacords fromId(Long id) {
        if (id == null) {
            return null;
        }
        BeaconReacords beaconReacords = new BeaconReacords();
        beaconReacords.setId(id);
        return beaconReacords;
    }
}
