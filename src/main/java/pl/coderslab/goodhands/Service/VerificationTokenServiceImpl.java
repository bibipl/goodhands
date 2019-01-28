package pl.coderslab.goodhands.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.coderslab.goodhands.repository.VerificationTokenRepository;
import pl.coderslab.goodhands.verificationToken.VerificationToken;

import java.util.List;

@Service
@Transactional
public class VerificationTokenServiceImpl implements VerificationTokenService{

    @Autowired
    VerificationTokenRepository verificationTokenRepository;

    @Override
    public List<VerificationToken> findAllByUserId(Long userId) {
        return verificationTokenRepository.findAllByUserId(userId);
    }

    @Override
    public VerificationToken findById(Long id) {
        return verificationTokenRepository.findById(id);
    }

    @Override
    public void save(VerificationToken verificationToken) {
        verificationTokenRepository.save(verificationToken);
    }

    @Override
    public void delete(VerificationToken verificationToken) {
        verificationTokenRepository.delete(verificationToken);
    }
}
