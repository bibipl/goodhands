package pl.coderslab.goodhands.Service;

import pl.coderslab.goodhands.foundation.Foundation;

import java.util.List;

public interface FoundationService {
    List<Foundation> findAll ();
    void save (Foundation foundation);
    void delete (Foundation foundation);
    Foundation findByName(String name);
    Foundation findByEmail(String email);
    Foundation findById(Long id);
}
