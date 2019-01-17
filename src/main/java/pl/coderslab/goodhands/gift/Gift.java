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

    private String numberOfBags;
    private String content; // to choose form item List
    private String subContent1;
    private String subContent2;

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

    private int completed = 0; // 0 empty, 1 - finished 1st stage, 2 - finfished 2nd stage etc.. 9- completed
    private boolean archieve = false;
}
