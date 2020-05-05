package com.licence.app.repository;

import com.licence.app.domain.BeaconReacords;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the BeaconReacords entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BeaconReacordsRepository extends JpaRepository<BeaconReacords, Long> {
}
