package entity;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
public class User {
    private long id;
    private String login;
    private String password;
    private final LocalDateTime REGISTRYDATE = LocalDateTime.now();
    private long educationId;
    private String name;
    private String country;
    private String language;
    private LocalDate birthday;

    public String toString(Optional<Education> education) {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", registryDate=" + REGISTRYDATE +
                ", education=\n" + education.toString() +
                ", name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", language='" + language + '\'' +
                ", birthday=" + birthday +
                '}';
    }
}
