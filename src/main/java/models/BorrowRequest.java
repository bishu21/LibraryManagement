package models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class BorrowRequest {
    Integer bookId;
    Integer userId;
    LocalDate dueDate;
    Integer copyId;
}
