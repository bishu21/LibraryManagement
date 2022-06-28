import models.*;
import service.ManagementService;
import service.ManagementServiceImpl;

import java.time.LocalDate;
import java.util.List;

public class LibraryApp {
    public static void main(String[] args) {


        ManagementService managementService = new ManagementServiceImpl();

        managementService.initliaze(5);

        User user = new User(123, "Bishwendra Choudhary");
        managementService.addUser(user);

        Book book = new Book(1, "Sherlock Holmes", List.of("bishwendra", "ram"),
                "Arihant Publication", List.of(1, 2, 3));

        Book book1 = new Book(2, "Maths", List.of("bishwendra", "ram"),
                "Arihant Publication", List.of(1, 2));
        managementService.addBooks(book);
        managementService.addBooks(book1);

        BorrowRequest request = new BorrowRequest(2, 123, LocalDate.now().plusDays(5), 2);
        managementService.borrowBook(request);

        BorrowRequest request1 = new BorrowRequest(1, 123, LocalDate.now().plusDays(5), 2);
        managementService.borrowBook(request1);

        managementService.print_borrowed_copies(123);

        ReturnRequest returnRequest = new ReturnRequest(1, 123, 1);
        managementService.returnBook(returnRequest);

        managementService.print_borrowed_copies(123);

        SearchRequest request2 = new SearchRequest(null, "ram", null);
        managementService.searchBook(request2);

    }
}
