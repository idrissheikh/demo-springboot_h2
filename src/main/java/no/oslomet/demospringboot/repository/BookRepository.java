package no.oslomet.demospringboot.repository;

import no.oslomet.demospringboot.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
