package no.oslomet.demospringboot.repository;

import no.oslomet.demospringboot.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
public interface AurthorRepository extends JpaRepository<Author, Long>{
}
