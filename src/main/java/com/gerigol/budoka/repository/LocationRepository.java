package com.gerigol.budoka.repository;

import com.gerigol.budoka.domain.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {

    boolean existsByName(String locationName);

    Location findByName(String locationName);
}
