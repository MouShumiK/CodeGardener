package com.moushumi.CodeGardener;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GardenerRepository extends JpaRepository<Gardener, Long> {
    Optional<Gardener>findGardenerByEmail(String email);

}
