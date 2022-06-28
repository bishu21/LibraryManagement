package models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
public class Library {
    Integer id;
    String name;
    List<Rack> racks;
    Integer capacity;
    List<User> users;
    List<Book> books;
}
