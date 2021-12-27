package edu.kpi.iasa.ka97.identity_service.repository;


import edu.kpi.iasa.ka97.identity_service.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
