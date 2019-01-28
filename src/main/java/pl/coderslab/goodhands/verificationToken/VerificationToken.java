package pl.coderslab.goodhands.verificationToken;

import javax.persistence.*;

import lombok.Data;
import pl.coderslab.goodhands.user.User;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

@Data
@Entity
public class VerificationToken {
    private static final int EXPIRATION = 60 * 24;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String token;

    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private User user;

    // standard constructors, getters and setters - not needed due to lombok
}

