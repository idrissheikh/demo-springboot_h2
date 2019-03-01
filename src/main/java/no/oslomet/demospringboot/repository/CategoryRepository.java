package no.oslomet.demospringboot.repository;

import no.oslomet.demospringboot.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
