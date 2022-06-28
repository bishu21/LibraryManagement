# LibraryManagement

A library management system is an automation system used to manage a library and the different resource management required in it like cataloging of books, allowing check out and return of books, invoicing, user management, etc.

For this problem, we have to design a library management system that can do a few of the above functionalities.

Requirements
Create a command-line application for the library management system with the following requirements.

Details about the Library:
The library will have one or more copies of multiple books
The library will have multiple racks and each rack can contain at most one copy of any book.
Each book will have the following properties:
Book ID
Title
Authors
Publishers
Details about Book Copies
There could be multiple copies of the same book.
Each book copy will have a unique ID.
Each book copy will be placed on a rack.
Each book copy can be borrowed by a user with a specific due date.
Every rack will have a unique rack number (numbered serially from 1 to n where n is the total number of racks).
Details about User:
User details: User ID, Name
A user can borrow a maximum of 5 books.
The functions that the library management system can do:
Create a library.
Add a book to the library. The book should be added to the first available rack.
Remove a book copy from the library.
Allow a user to borrow a book copy given the book id, user id, and due date. The first available copy based on the rack number should be provided.
Allow a user to borrow a book copy given the book copy id, user id, and due date.
Allow a user to return a book copy given the book copy id. The book should be added to the first available rack.
Allow a user to print the book copy ids of all the books borrowed by them.
Allow a user to search for books using few book properties (Book ID, Title, Author, Publisher). Searching should return details about all the book copies that match the search query.
Input/Output Format
The code should strictly follow the input/output format and will be tested with provided test cases.

Input Format
Multiple lines with each line containing a command.

Possible commands:

create_library <library_id> <no_of_racks>
add_book <book_id> <title> <comma_separated_authors> <comma_separated_publishers> <comma_separated_book_copy_ids>
remove_book_copy <book_copy_id>
borrow_book <book_id> <user_id> <due_date>
borrow_book_copy <book_copy_id> <user_id> <due_date>
return_book_copy <book_copy_id>
print_borrowed <user_id>
search <attribute> <attribute_value>
Possible values of attribute: book_id, author, publisher
exit
Stop taking the input when you encounter the word exit.

Note: You can assume that there would be no spaces in the book title or names of publishers/authors
