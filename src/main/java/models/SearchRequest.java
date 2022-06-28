package models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SearchRequest {
    Integer bookId;
    String bookAuthor;
    String publication;
}
