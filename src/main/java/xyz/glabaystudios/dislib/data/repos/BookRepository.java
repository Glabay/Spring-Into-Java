package xyz.glabaystudios.dislib.data.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.glabaystudios.dislib.data.model.Book;

import java.util.Collection;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findByISBN10(Long ISBN10);
    Optional<Book> findByISBN13(Long ISBN13);
    Optional<Book> getByShelfIdAndOwnerDiscordId(Long aLong, Long ownerDiscordId);

    Collection<Book> findByShelfIdAndOwnerDiscordId(Long shelfId, Long ownerDiscordId);
    Collection<Book> findByShelfId(Long shelfId);
}