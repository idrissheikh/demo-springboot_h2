package no.oslomet.demospringboot.repository;

import no.oslomet.demospringboot.model.Shipping;
import org.springframework.data.jpa.repository.JpaRepository;
public interface ShippingRepository extends JpaRepository<Shipping, Long> {}

