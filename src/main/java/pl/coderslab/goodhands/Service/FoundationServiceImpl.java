package pl.coderslab.goodhands.Service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.coderslab.goodhands.foundation.Foundation;
import pl.coderslab.goodhands.repository.FoundationRepository;

import java.util.List;

@Service
@Transactional
public class FoundationServiceImpl implements FoundationService {

    private final FoundationRepository foundationRepository;

    public FoundationServiceImpl(FoundationRepository foundationRepository) {
        this.foundationRepository = foundationRepository;
    }

    @Override
    public List<Foundation> findAll() {
        return foundationRepository.findAll();
    }

    @Override
    public void save(Foundation foundation) {
        foundationRepository.save(foundation);
    }

    @Override
    public void delete(Foundation foundation) {
        foundationRepository.delete(foundation);

    }

    @Override
    public Foundation findByName(String name) {
        return foundationRepository.findByName(name);
    }

    @Override
    public Foundation findByEmail(String email) {
        return foundationRepository.findByEmail(email);
    }

    @Override
    public Foundation findById(Long id) {
        return foundationRepository.findById(id);
    }
}
