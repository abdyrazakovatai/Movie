package java15.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "cinemas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Cinema {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(nullable = false,unique = true)
    String name;
    @Column(name = "image_logo",length = 1000)
    String imageLogo;


    @ToString.Exclude
    @ManyToMany(mappedBy = "cinemas", fetch = FetchType.EAGER,cascade = { CascadeType.MERGE, CascadeType.REFRESH})//persist
    Set<Movie> movies = new HashSet<>();

    @ToString.Exclude
    @OneToMany(        cascade = {CascadeType.MERGE,CascadeType.ALL},fetch = FetchType.EAGER)//mappedBy cinema
    Set<Hall> halls = new HashSet<>();

    public Cinema(String name, String imageLogo) {
        this.name = name;
        this.imageLogo = imageLogo;
    }

    public Cinema(String name, String imageLogo, Set<Hall> halls) {
        this.name = name;
        this.imageLogo = imageLogo;
        this.halls = halls;
    }

}
