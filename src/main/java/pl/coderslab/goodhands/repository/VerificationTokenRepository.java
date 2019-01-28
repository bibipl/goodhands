package pl.coderslab.goodhands.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.goodhands.verificationToken.VerificationToken;

import java.util.List;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Integer> {
    List<VerificationToken> findAllByUserId (Long userId);
    VerificationToken findById (Long id);
}
