package service;

import models.*;

public interface ManagementService {
    void initliaze(Integer capacity);
    void addBooks(Book request);
    void searchBook(SearchRequest search);
    void removeBookAllCopies(Integer id);
    void borrowBook(BorrowRequest request);
    void returnBook(ReturnRequest returnRequest);
    void print_borrowed_copies(Integer userId);
    void addUser(User user);
}
