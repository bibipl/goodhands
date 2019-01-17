package pl.coderslab.goodhands.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.goodhands.gift.Gift;

import java.util.List;

public interface GiftRepository extends JpaRepository<Gift, Integer> {
    List<Gift> findAllByUserId (Long id);
    List<Gift> findAllByFoundationId (Long id);
    Gift findById (Long id);

}
