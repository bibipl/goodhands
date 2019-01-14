package pl.coderslab.goodhands.gift;

import lombok.Data;
import pl.coderslab.goodhands.foundation.Foundation;
import pl.coderslab.goodhands.user.User;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Entity
@Table(name="gifts")
public class Gift {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int numberOfBags;
    private String content; // to choose form item List

    @ManyToOne (fetch = FetchType.EAGER)
    private User user;

    @ManyToOne (fetch = FetchType.EAGER)
    private Foundation foundation;

    private String street;
    private String town;
    private String postal;
    private String phone;

    private LocalDate date;
    private LocalTime time;
    private String comment;

    private boolean completed = false;
    private boolean archieve = false;
}
