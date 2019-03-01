package no.oslomet.demospringboot.repository;

import no.oslomet.demospringboot.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdersRepository extends JpaRepository<Orders, Long> {
}


