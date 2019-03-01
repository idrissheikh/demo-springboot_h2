package no.oslomet.demospringboot.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Data
@NoArgsConstructor



public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String fristName;
    private String lastName;
    private String rating;

    //book_Author
    @ManyToMany(mappedBy = "authorList",  cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private List<Book> bookList = new ArrayList<>();


    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", fristName='" + fristName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", rating='" + rating + '\'' +
                '}';
    }
}
