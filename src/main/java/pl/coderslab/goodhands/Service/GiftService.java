package pl.coderslab.goodhands.Service;

import pl.coderslab.goodhands.gift.Gift;

import java.util.List;

public interface GiftService {

    List<Gift> findAll();
    List<Gift> findAllByUserId (Long id);
    List<Gift> findAllByFoundationId (Long id);
    Gift findById (Long id);
    void save (Gift gift);
    void delete (Gift gift);
}
