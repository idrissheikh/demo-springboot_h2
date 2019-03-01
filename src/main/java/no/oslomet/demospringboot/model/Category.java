package no.oslomet.demospringboot.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor

public class Category {

//    //Author_Category
//    @ManyToMany(mappedBy ="categoryList")
//    private List<Author> authorList;


   //Bok_category
    @OneToMany(mappedBy ="category")
    private List<Book> bookList;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  long id;
    private String name;


 @Override
 public String toString() {
  return "Category{" +
          //"bookList=" + bookList +
          ", id=" + id +
          ", name='" + name + '\'' +
          '}';
 }
}
