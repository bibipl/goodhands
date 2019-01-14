package pl.coderslab.goodhands.foundation;

import lombok.Data;
import pl.coderslab.goodhands.gift.Gift;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
@Entity
@Table(name = "foundations")
public class Foundation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Email
    @Size(max=245)
    @NotBlank
    @Column(name = "email", unique = true)
    private String email;

    private String address;
    private String phone;

    @OneToMany (fetch = FetchType.EAGER, mappedBy = "user")
    private Set<Gift> gifts;

}
