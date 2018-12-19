package pl.coderslab.goodhands.user;
import org.springframework.security.core.GrantedAuthority;
import java.util.Collection;
import org.springframework.security.core.userdetails.User;

public class CurrentUser extends User {
    private final pl.coderslab.goodhands.user.User user;
    public CurrentUser(String username, String password, Collection<?
                extends GrantedAuthority> authorities, pl.coderslab.goodhands.user.User user) {
        super(username, password, authorities); this.user = user;
    }
    public pl.coderslab.goodhands.user.User getUser() {return user;}
}
