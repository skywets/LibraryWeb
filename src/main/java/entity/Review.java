package entity;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
public class Review {
    private long id;
    private long userId;
    private long bookId;
    private String text;
    private final LocalDateTime REVIEWDATE = LocalDateTime.now();

    public Review(long userId, long bookId, String text) {
        this.userId = userId;
        this.bookId = bookId;
        this.text = text;
    }
}
