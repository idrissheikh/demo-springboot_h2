package no.oslomet.demospringboot.Controller;

import no.oslomet.demospringboot.model.*;
import no.oslomet.demospringboot.repository.*;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class HomeController {

    //depaance injection
    @Autowired
    private AurthorRepository aurthorRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private OrdersRepository ordersRepository;
    @Autowired
    private ShippingRepository shippingRepository;

    @GetMapping({"/", "/list"})
    public String list(Model model) {
        List<Book> books = bookRepository.findAll();
        model.addAttribute("books", books);
        Book book = new Book();
        model.addAttribute("book", book);
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("authors", aurthorRepository.findAll());
        return "index";

    }



    @PostMapping("/saveBook")
    public String save(@ModelAttribute("book") Book book, @RequestParam("category") long categoryId,
                       @RequestParam("authors") List<Long> authorList) {
        System.out.println(Arrays.toString(authorList.toArray()));
        List<Author> authors = new ArrayList<>();
        for(Long id : authorList) authors.add(aurthorRepository.findById(id).get());
        book.setCategory(categoryRepository.findById(categoryId).get());
        book.setAuthorList(authors);
        System.out.println("size: " + book.getAuthorList().size());
        bookRepository.save(book);
        return "redirect:/list";
    }


    @PostMapping("/saveBestilling")
    public String saveBestilling(@ModelAttribute("shipping") Shipping shipping ,
                                 @RequestParam("id") long bookId
                                ) {
        Book book = bookRepository.findById(bookId).get();
        System.out.println("model book" + book.getCategory().getName() );
        System.out.println(book.getAuthorList().size());



        Orders orders = new Orders();
        List<Book> bookList = new ArrayList<>();
        bookList.add(book);

        String date = getDateTime();
        orders.setDate(date);
        orders.setBookList(bookList);
        orders.setShipping(shipping);
        List<Orders> ordersList = new ArrayList<>();
        ordersList.add(orders);
        shipping.setOrdersList(ordersList);


     //   System.out.println("Order: " + orders);
        shippingRepository.save(shipping);
        ordersRepository.save(orders);

        return "redirect:/orderlist";
    }

    //edit book
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, Model model) {
        Book book = this.bookRepository.findById(Long.parseLong(id)).get();
        List<Book> books = bookRepository.findAll();

        //
        List<Category> categoryList =categoryRepository.findAll();
        List<Author> authorList = aurthorRepository.findAll();
        model.addAttribute("categories", categoryList);
        model.addAttribute("authors",authorList);
        model.addAttribute("book", book);
        model.addAttribute("books", books);

        return "index";
    }
    //delete book
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") String id, Model model) {
        this.bookRepository.deleteById(Long.parseLong(id));
        return "redirect:/list";
    }


    // get date
    public static String getDateTime() {
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//dd/MM/yyyy
        Date now = new Date();
        String strDate = sdfDate.format(now);

        return strDate;
    }




        //order boks
    @GetMapping("/shipping/{id}")
    public String bestille(@PathVariable("id") String id, Model model) {
        Book book = this.bookRepository.findById(Long.parseLong(id)).get();


        String date = getDateTime();
        Shipping shipping = new Shipping();
//        Orders orders = new Orders();
//        orders.setDate(date);
       model.addAttribute("book", book);
       model.addAttribute("shipping", shipping);

        return "shipping";
    }

    //orderhistory
    @GetMapping({"/orderlist"})
    public String Orderlist(Model model) {
        model.addAttribute("orders", ordersRepository.findAll());
        model.addAttribute("books", bookRepository.findAll());
        System.out.println(Arrays.toString(ordersRepository.findAll().toArray()));

        return "orderlist";
    }


    //deleteOrder
    @GetMapping("/deleteOrder/{id}")
    public String deleteOrder(@PathVariable("id") String id, Model model) {
        this.ordersRepository.deleteById(Long.parseLong(id));
        return "redirect:/Orderlist";
    }



    @PostMapping("/search")
    public String search(@RequestParam("title") String title, Model model ) {
        System.out.println("han var her :)");
       // Book book = this.bookRepository.findById(Long.parseLong(id)).get();
        Book searchBook = null;
       for(Book book : bookRepository.findAll() ) {
           if(book.getTitle().equals(title)) searchBook = book;
       }

        model.addAttribute("books", bookRepository.findAll());
        model.addAttribute("book", new Book());
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("authors", aurthorRepository.findAll());
        model.addAttribute("searchBook", searchBook);

        return "index";
    }

    //find book
//    public Book findUsingIterator(
//            String title, List<Book> bookList) {
//        Iterator<Book> iterator = bookList.iterator();
//        while (iterator.hasNext()) {
//            Book book = iterator.next();
//            if (book.getTitle().equals(title)) {
//                return book;
//            }
//        }
//        return null;
//    }


//    @GetMapping({"/bookByCategory"})
//    public String bookByCategory(Model model) {
//        List<C> books = bookRepository.findAll();
//        model.addAttribute("books", books);
//        Book book = new Book();
//        model.addAttribute("book", book);
//        return "index";
//    }
}



