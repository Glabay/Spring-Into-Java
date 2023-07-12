package xyz.glabaystudios.dislib.data.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookDTO {

    private String title;
    private String author;
    private String theme;
    private String description;
    private String publisher;
    private String publishedDate;

    private Long shelfId;
    private Long ownerDiscordId;
    private Long ISBN10;
    private Long ISBN13;
}
