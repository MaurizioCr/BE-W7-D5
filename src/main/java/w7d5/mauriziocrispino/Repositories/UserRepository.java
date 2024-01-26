package w7d5.mauriziocrispino.Repositories;

import w7d5.mauriziocrispino.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository <User, UUID> {
    Optional<User> findByEmail(String email);
}

