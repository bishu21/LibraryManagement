package models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Rack {
    Integer id;
    Boolean isEmpty;
    Integer bookId;
    Integer copyId;
}
