package xyz.glabaystudios.dislib.data.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.glabaystudios.dislib.data.model.RegisteredUser;

import java.util.Optional;

public interface RegisteredUserRepository extends JpaRepository<RegisteredUser, Long> {
    Optional<RegisteredUser> findByUsername(String username);
}