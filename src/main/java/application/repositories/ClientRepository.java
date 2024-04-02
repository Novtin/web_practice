package application.repositories;

import application.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {
    Client findClientByEmail(String email);
    Client findClientByClientId(int id);
    @Query("SELECT c FROM Client c WHERE c.email <> :email")
    List<Client> findAllExceptEmail(@Param("email") String email);

}
