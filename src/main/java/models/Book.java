package models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.Objects;

@Data
@AllArgsConstructor
public class Book {
    Integer id;
    String title;
    List<String> authors;
    String publication;
    List<Integer> copiesCount;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(id, book.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
