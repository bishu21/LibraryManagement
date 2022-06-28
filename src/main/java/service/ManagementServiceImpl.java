package service;

import models.*;

import java.util.*;
import java.util.stream.Collectors;

public class ManagementServiceImpl implements ManagementService {
    private Library library;

    private Map<Integer, List<BorrowRequest>> borrowMap = new HashMap<>();

    @Override
    public void initliaze(Integer capacity) {
        System.out.println("Initializing the library with capacity="+capacity);
        library = new Library();
        library.setCapacity(capacity);
        List<Rack> racks = new ArrayList<>();
        for (int i=0;i<capacity;i++)
            racks.add(new Rack(i+1, true, null, null));
        library.setRacks(racks);
        System.out.println("Created Racks : "+racks);
    }

    @Override
    public void addBooks(Book request) {
        System.out.println("Adding book to library, current books -: "+library.getBooks());
        if (library == null) {
            // library should be not null
            throw new RuntimeException("Library should be initialize first.");
        }


        if (library.getCapacity() < request.getCopiesCount().size()) {
            // throw not able to store exception
            throw new RuntimeException("Library does not have capcity to store all copies, current " +
                    "capcity of library = "+ library.getCapacity());
        }

        List<Book> books = library.getBooks() == null ? new ArrayList<>(): library.getBooks();
        books.add(request);
        library.setBooks(books);
        library.setCapacity(library.getCapacity()-request.getCopiesCount().size());
        int count=0;
        for(Rack rack: library.getRacks()) {
            if (rack.getIsEmpty()) {
                rack.setBookId(request.getId());
                rack.setCopyId(request.getCopiesCount().get(count++));
                rack.setIsEmpty(false);

            }

            if(count == request.getCopiesCount().size()) break;
        }
        System.out.println("After added book to library, books "+library.getBooks());
        System.out.println("Racks ---> "+library.getRacks());

    }

    @Override
    public void searchBook(SearchRequest search) {

        System.out.println("Searching the book in library for "+ search);

        List<Integer> books = library.getBooks().stream().filter(item ->
                item.getPublication().equals(search.getPublication())
                        || item.getAuthors().contains(search.getBookAuthor())
                        || item.getId().equals(search.getBookId())).peek(item -> System.out.println(item))
                .map(item -> item.getId()).collect(Collectors.toList());

        List<Rack> racks = library.getRacks().stream().filter(item -> books.contains(item.getBookId()))
                .collect(Collectors.toList());

        System.out.println("Found book in racks --->"+racks);

    }

    @Override
    public void removeBookAllCopies(Integer id) {

        System.out.println("Removing the books from library with bookid = " + id);
        System.out.println("Available books in library --->"+ library.getBooks());

        Optional<Book> book = library.getBooks().stream().filter(item -> item.getId() == id).findFirst();

        if (!book.isPresent()) {
            // can not delete the book from library not found exception
            throw new RuntimeException("Book is not found in library to remove with id"+id);
        }

        library.getBooks().remove(book.get().getId());

        library.setCapacity(library.getCapacity() + book.get().getCopiesCount().size());

        library.getRacks().stream().filter(item -> item.getBookId() == id).forEach(item -> {
            item.setBookId(null);
            item.setCopyId(null);
            item.setIsEmpty(true);
        });

        System.out.println("After removing books from library --->"+ library.getBooks());
        System.out.println("Racks looks like --> "+library.getRacks());


    }

    @Override
    public void borrowBook(BorrowRequest request) {

        System.out.println("Borrow the book with "+request);

        if(!library.getUsers().contains(new User(request.getUserId(), null))) {
            // invalid user exception
            throw new RuntimeException("user is not registered in library, " + request.getUserId() + " Availabe users"+ library.getUsers());
        }

        if(!library.getBooks().stream().map(item -> item.getId()).collect(Collectors.toSet())
                .contains(request.getBookId())) {
            // invalid book id borrowing
            throw new RuntimeException("Invalid bookId to borrow");
        }


        Optional<Rack> temp =library.getRacks().stream()
                .filter(item -> !item.getIsEmpty() && item.getBookId()== request.getBookId()).findFirst();
        if (!temp.isPresent()) {
            // book is not available in rack
            throw new RuntimeException("Book is not available for temporaryly in racks");
        }

        List<BorrowRequest> borrowRequestList = borrowMap.getOrDefault(request.getUserId(), new ArrayList<>());
        request.setCopyId(temp.get().getCopyId());
        borrowRequestList.add(request);
        borrowMap.put(request.getUserId(), borrowRequestList);

        temp.get().setIsEmpty(true);
        temp.get().setBookId(null);
        temp.get().setCopyId(null);



        System.out.println("Racks looks like ---->"+library.getRacks());
    }

    @Override
    public void returnBook(ReturnRequest returnRequest) {

        System.out.println("Returning the book with "+ returnRequest);

        List<BorrowRequest> orders = borrowMap.get(returnRequest.getUserId());

        Optional<BorrowRequest> borrowRequest= orders.stream().filter(item -> item.getBookId() == returnRequest.getBookId() &&
                item.getCopyId() == returnRequest.getCopyId()).findFirst();

        if(!borrowRequest.isPresent()) {
            // invalid return to library
            throw new RuntimeException("Invalid book is returning by user");
        }

        orders.remove(borrowRequest.get());

        Optional<Rack> rack = library.getRacks().stream().filter(item -> item.getIsEmpty()).findFirst();

        rack.get().setBookId(returnRequest.getBookId());
        rack.get().setCopyId(returnRequest.getCopyId());
        rack.get().setIsEmpty(false);

        System.out.println("Current libray Racks  " + library.getRacks());

    }

    @Override
    public void print_borrowed_copies(Integer userId) {
        System.out.println("printing borrowed copies for userId "+ userId +" --------->");
        System.out.println(borrowMap.get(userId));
    }

    @Override
    public void addUser(User user) {
        System.out.println("Adding the user in library,  "+ user);
        List<User> users = library.getUsers() == null ? new ArrayList<>() : library.getUsers();
        users.add(user);
        library.setUsers(users);
        System.out.println(users);
    }
}
