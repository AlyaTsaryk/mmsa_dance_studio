package edu.kpi.iasa.ka97.request_service.repository;

import edu.kpi.iasa.ka97.request_service.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StatusRepository extends JpaRepository<Status, Long> {
    Optional<Status> findStatusByName(String name);
}
