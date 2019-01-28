package pl.coderslab.goodhands.Service;

import pl.coderslab.goodhands.verificationToken.VerificationToken;

import java.util.List;

public interface VerificationTokenService {
    List<VerificationToken> findAllByUserId (Long userId);
    VerificationToken findById (Long id);
    void save (VerificationToken verificationToken);
    void delete (VerificationToken verificationToken);
}
