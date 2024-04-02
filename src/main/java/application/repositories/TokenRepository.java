package application.repositories;

import application.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface TokenRepository extends JpaRepository<Token, Integer> {
    @Transactional
    void deleteByToken(String token);
}
