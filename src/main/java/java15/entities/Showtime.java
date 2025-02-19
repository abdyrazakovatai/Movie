package java15.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "show_times")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Showtime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(name = "start_time")
    LocalDateTime startTime;
    Double price;

    @ToString.Exclude
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "showtime",cascade = CascadeType.PERSIST, orphanRemoval = true)
    List<Ticket> tickets = new ArrayList<>();

    @ManyToOne
    Movie movie;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE})
    Hall hall;

    public Showtime(LocalDateTime startTime, Double price) {
        this.startTime = startTime;
        this.price = price;

    }
}
