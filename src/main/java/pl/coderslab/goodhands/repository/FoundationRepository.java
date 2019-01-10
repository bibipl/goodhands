package pl.coderslab.goodhands.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.goodhands.foundation.Foundation;

public interface FoundationRepository extends JpaRepository<Foundation, Integer> {
    Foundation findByName(String name);
    Foundation findByEmail(String email);
    Foundation findById(Long id);
}






