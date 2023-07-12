package xyz.glabaystudios.dislib.data.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.glabaystudios.dislib.data.model.BookShelf;

import java.util.Optional;

public interface BookShelfRepository extends JpaRepository<BookShelf, Long> {
    Optional<BookShelf> findByOwnerDiscordId(Long ownerDiscordId);
}