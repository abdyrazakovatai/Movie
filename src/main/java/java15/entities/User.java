package java15.entities;

import jakarta.persistence.*;
import java15.Enum.Role;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(unique = true, nullable = false)
    String email;
    String password;
    @Column(name = "phone_number")
    String phoneNumber;
    @Enumerated(EnumType.STRING)
    Role role;
    @Column(name = "registraton_date")
    LocalDateTime registrationDate;
    String name;

    @PrePersist
    public void registerTime() {
        registrationDate = LocalDateTime.now();
    }

    @OneToOne(cascade = CascadeType.ALL)
    Card card;

    @OneToMany(mappedBy = "user")
    List<Ticket> tickets;

    public User(String email, String password, String phoneNumber, Role role, LocalDateTime registrationDate, String name, Card card) {
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.role = role;
        this.registrationDate = registrationDate;
        this.name = name;
        this.card = card;
    }
}
