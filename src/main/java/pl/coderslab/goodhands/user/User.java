package pl.coderslab.goodhands.user;

import lombok.Data;
import pl.coderslab.goodhands.gift.Gift;
import pl.coderslab.goodhands.role.Role;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
@Entity
@Table (name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @NotBlank
    private String password;

    @Transient
    private String passwordCheck;

    @Email
    @Size(max=245)
    @NotBlank
    @Column(name = "email", unique = true)
    private String email;

    private int enabled = 0;

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    @OneToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private Set<Gift> gifts;

    @Transient
    private boolean isUser = false;

    @Transient
    private boolean isAdmin = false;


    public boolean getIsUser() {
        return isUser;
    }

    public void setIsUser(boolean isUser) {
        this.isUser = isUser;
    }

    public boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public void addRole (Role role) {
        this.roles.add(role);
    }
}
