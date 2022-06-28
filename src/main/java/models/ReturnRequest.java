package models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReturnRequest {
    Integer bookId;
    Integer userId;
    Integer copyId;
}
