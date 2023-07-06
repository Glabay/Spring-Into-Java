package xyz.glabaystudios.dislib.data.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.glabaystudios.dislib.data.model.BookShelf;

import java.util.Collection;

public interface BookShelfRepository extends JpaRepository<BookShelf, Long> {
    Collection<BookShelf> findByOwnerDiscordId(Long ownerDiscordId);
}