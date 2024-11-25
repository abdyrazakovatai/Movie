package java15.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Entity
@Table(name = "cards")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(length = 16,unique = true)
    String cardNumber;
    Double summa;
    @Column(name = "expiration_date")
    LocalDate expirationDate;

    @OneToOne(cascade = {CascadeType.PERSIST})
    User user;

    public Card(String cardNumber, Double summa, LocalDate expirationDate) {
        this.cardNumber = cardNumber;
        this.summa = summa;
        this.expirationDate = expirationDate;

    }
}
