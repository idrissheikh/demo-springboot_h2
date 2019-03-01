package no.oslomet.demospringboot.model;

import javax.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;


@Entity
@Data
@NoArgsConstructor

public class Shipping {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String firstName;
    private String lastName;
    private String address;
    private int postalCode;


    //usikker har
    @OneToMany(mappedBy="shipping" , cascade = CascadeType.ALL)
    private List<Orders>  ordersList;

    @Override
    public String toString() {
        return "Shipping{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                ", postalCode=" + postalCode +
                '}';
    }
}
