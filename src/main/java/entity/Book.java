package entity;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
public class Book {
    private long id;
    private long catalogId;
    private String title;
    private String author;
    private String textBook;
    private int countOfPages;
    private LocalDate publishingDate;
    private boolean popular;
    private boolean newRelease;
    private String genre;
}
