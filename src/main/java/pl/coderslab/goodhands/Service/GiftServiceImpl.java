package pl.coderslab.goodhands.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.coderslab.goodhands.gift.Gift;
import pl.coderslab.goodhands.repository.GiftRepository;

import java.util.List;

@Service
@Transactional
public class GiftServiceImpl implements GiftService {

    @Autowired
    GiftRepository giftRepository;

    @Override
    public List<Gift> findAll() {
        return giftRepository.findAll();
    }

    @Override
    public List<Gift> findAllByUserId(Long id) {
        return giftRepository.findAllByUserId(id);
    }

    @Override
    public List<Gift> findAllByFoundationId(Long id) {
        return giftRepository.findAllByFoundationId(id);
    }

    @Override
    public Gift findById(Long id) {
        return giftRepository.findById(id);
    }

    @Override
    public void save(Gift gift) {
        giftRepository.save(gift);
    }

    @Override
    public void delete(Gift gift) {
        giftRepository.delete(gift);
    }
}
