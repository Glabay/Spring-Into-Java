package xyz.glabaystudios.dislib.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import xyz.glabaystudios.dislib.data.dto.BookDTO;
import xyz.glabaystudios.dislib.data.dto.BookShelfDTO;
import xyz.glabaystudios.dislib.data.model.BookShelf;
import xyz.glabaystudios.dislib.data.repos.BookShelfRepository;
import xyz.glabaystudios.dislib.util.DateTimeUtils;

@Service
@RequiredArgsConstructor
public class BookShelfService implements DateTimeUtils {
    private final BookShelfRepository bookShelfRepository;
    private final BookService bookService;

    public BookShelfDTO getBookShelfForUser(Long discordId) {
        var bookShelf = bookShelfRepository.findByOwnerDiscordId(discordId);
        if (bookShelf.isPresent()) {
            BookShelfDTO shelf = convertObjectToDTO(bookShelf.get());
            shelf.getBooks().addAll(bookService.findAllForShelfIdForUser(shelf.getShelfId(), shelf.getOwnerDiscordId()));
            return shelf;
        }
        return null;
    }

    public void addBookToShelf(BookShelfDTO shelf, BookDTO book) {
        book.setShelfId(shelf.getShelfId());
        if (bookService.updateBook(book))
            System.out.println("Successfully added book to shelf.");
        else throw new RuntimeException("Error adding book to shelf");
    }

    public BookShelf updateBookShelf(BookShelf shelf, BookShelfDTO dto) {
        shelf.setShelfName(dto.getShelfName());
        shelf.setShelfDescription(dto.getShelfDescription());
        shelf.setOwnerDiscordId(dto.getOwnerDiscordId());
        shelf.setUpdatedOn(getCurrentDateAndTime());
        return shelf;
    }

    public BookShelf createNewBookShelf(String name, String description, Long ownerDiscordId) {
        BookShelf newShelf = new BookShelf();
        newShelf.setShelfName(name);
        newShelf.setShelfDescription(description);
        newShelf.setOwnerDiscordId(ownerDiscordId);
        newShelf.setCreatedOn(getCurrentDateAndTime());
        newShelf.setUpdatedOn(getCurrentDateAndTime());
        return newShelf;
    }

    private BookShelfDTO convertObjectToDTO(BookShelf model) {
        BookShelfDTO dto = new BookShelfDTO();
            dto.setShelfId(model.getUid());
            dto.setOwnerDiscordId(model.getOwnerDiscordId());
            dto.setShelfName(model.getShelfName());
            dto.setShelfDescription(model.getShelfDescription());
        return dto;
    }
}
