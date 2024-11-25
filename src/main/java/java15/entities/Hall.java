package java15.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "halls")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Hall {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    @Column(name = "seat_count")
    int seatCount;

    @ToString.Exclude
    @OneToMany(fetch = FetchType.EAGER,cascade = {CascadeType.ALL,CascadeType.MERGE})
    Set<Showtime> showTimes= new HashSet<>();

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.EAGER)//persist
    private Cinema cinema;

    public Hall(String name, int seatCount) {
        this.name = name;
        this.seatCount = seatCount;

    }
}
