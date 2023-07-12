package xyz.glabaystudios.dislib.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import xyz.glabaystudios.dislib.data.dto.BookDTO;
import xyz.glabaystudios.dislib.data.model.Book;
import xyz.glabaystudios.dislib.data.repos.BookRepository;
import xyz.glabaystudios.dislib.util.DateTimeUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookService implements DateTimeUtils {
    private final BookRepository bookRepository;

    public void save(Book book) {
        bookRepository.save(book);
    }

    public boolean updateBook(BookDTO dto) {
        var book = bookRepository.getByShelfIdAndOwnerDiscordId(dto.getShelfId(), dto.getOwnerDiscordId());
        if (book.isPresent()) {
            Book updatedBook = updateBookObject(book.get(), dto);
            bookRepository.save(updatedBook);
            return true;
        }
        return false;
    }

    public List<BookDTO> findAllForShelfIdForUser(Long shelfId, Long discordId) {
        return bookRepository.findByShelfIdAndOwnerDiscordId(shelfId, discordId).stream().map(this::convertObjectToDTO).collect(Collectors.toList());
    }

    public BookDTO getBookDtoForIsbn10(Long isbn10) {
        var book = bookRepository.findByISBN10(isbn10);
        return book.map(this::convertObjectToDTO).orElse(null);
    }

    public BookDTO getBookDtoForIsbn13(Long isbn13) {
        var book = bookRepository.findByISBN10(isbn13);
        return book.map(this::convertObjectToDTO).orElse(null);
    }

    public List<BookDTO> findAllForShelfId(Long shelfId) {
        return bookRepository.findByShelfId(shelfId).stream().map(this::convertObjectToDTO).collect(Collectors.toList());
    }

    public List<BookDTO> findAll() {
        return bookRepository.findAll().stream().map(this::convertObjectToDTO).collect(Collectors.toList());
    }

    private Book updateBookObject(Book model, BookDTO dto) {
        model.setTitle(dto.getTitle());
        model.setAuthor(dto.getAuthor());
        model.setTheme(dto.getTheme());
        model.setDescription(dto.getDescription());
        model.setPublisher(dto.getPublisher());
        model.setPublishedDate(dto.getPublishedDate());
        model.setShelfId(dto.getShelfId());
        model.setOwnerDiscordId(dto.getOwnerDiscordId());
        model.setISBN10(dto.getISBN10());
        model.setISBN13(dto.getISBN13());
        model.setUpdatedOn(getCurrentDateAndTime());
        return model;
    }

    private Book createNewBookObject(BookDTO dto) {
        Book newBook = new Book();
        newBook.setTitle(dto.getTitle());
        newBook.setAuthor(dto.getAuthor());
        newBook.setTheme(dto.getTheme());
        newBook.setDescription(dto.getDescription());
        newBook.setPublisher(dto.getPublisher());
        newBook.setPublishedDate(dto.getPublishedDate());
        newBook.setShelfId(newBook.getUid());
        newBook.setOwnerDiscordId(dto.getOwnerDiscordId());
        newBook.setISBN10(dto.getISBN10());
        newBook.setISBN13(dto.getISBN13());
        newBook.setCreatedOn(getCurrentDateAndTime());
        newBook.setUpdatedOn(getCurrentDateAndTime());

        return newBook;
    }

    private BookDTO convertObjectToDTO(Book object) {
        BookDTO dto = new BookDTO();
        dto.setTitle(object.getTitle());
        dto.setAuthor(object.getAuthor());
        dto.setTheme(object.getTheme());
        dto.setDescription(object.getDescription());
        dto.setPublisher(object.getPublisher());
        dto.setPublishedDate(object.getPublishedDate());
        dto.setShelfId(object.getShelfId());
        dto.setOwnerDiscordId(object.getOwnerDiscordId());
        dto.setISBN10(object.getISBN10());
        dto.setISBN13(object.getISBN13());

        return dto;
    }
}
