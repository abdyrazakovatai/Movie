package java15.entities;

import jakarta.persistence.*;
import java15.Enum.Genre;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "movies")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String image;
    @Column(unique = true)
    String movieName;
    int duration;
    @Enumerated(EnumType.STRING)
    Genre genre;
    @Column(name = "release_date")
    LocalDate releaseDate;
    @Column(name = "age_rating")
    int ageRating;
    @Column(name = "thriller_url")
    String thrillerUrl;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    MovieInfo movieInfo;

    @ToString.Exclude
    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    Set<Showtime> showTimes;

    @ToString.Exclude
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
//    @JoinTable(
//            name = "cinema_movie",
//            joinColumns = @JoinColumn(name = "movie_id"),
//            inverseJoinColumns = @JoinColumn(name = "cinema_id")
//    )
    Set<Cinema> cinemas = new HashSet<>();

    public Movie(String image, String movieName, int duration, Genre genre, LocalDate releaseDate, int ageRating, String thrillerUrl, MovieInfo movieInfo, Set<Showtime> showTimes, Set<Cinema> cinemas) {
        this.image = image;
        this.movieName = movieName;
        this.duration = duration;
        this.genre = genre;
        this.releaseDate = releaseDate;
        this.ageRating = ageRating;
        this.thrillerUrl = thrillerUrl;
        this.movieInfo = movieInfo;
        this.showTimes = showTimes;
        this.cinemas = cinemas;
    }
}
