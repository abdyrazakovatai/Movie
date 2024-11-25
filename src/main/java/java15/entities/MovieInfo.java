package java15.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "movie_infos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MovieInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String director;
    String actor;
    String country;
    String language;
    String description;

    @ToString.Exclude
    @OneToOne(mappedBy = "movieInfo", cascade = CascadeType.PERSIST)
    Movie movie;
}
