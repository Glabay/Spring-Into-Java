package xyz.glabaystudios.dislib.data.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.glabaystudios.dislib.data.model.Book;

import java.util.Collection;

public interface BookRepository extends JpaRepository<Book, Long> {
    Collection<Book> findByShelfIdAndOwnerDiscordId(Long shelfId, Long ownerDiscordId);
}