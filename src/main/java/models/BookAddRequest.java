package models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class BookAddRequest {
    String title;
    List<String> authors;
    String publication;
}
