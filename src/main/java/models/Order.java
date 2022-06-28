package models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class Order {
    Integer bookId;
    Integer copyId;
    String userId;
    LocalDate dueDate;
}
