package com.licence.app.repository;

import com.licence.app.domain.Beacon;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Beacon entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BeaconRepository extends JpaRepository<Beacon, Long> {
}
