import java.io.*;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;

public class LibraryMain {
    static Scanner k = new Scanner(System.in);

    // These are the ArrayLists containing all the books and users - all functions on them must be done on the same instance of the LibraryMain class.
    public ArrayList<User> userList = new ArrayList<>();
    public ArrayList<Book> bookList = new ArrayList<>();

    // Reads the libraryRead.txt file and stores books into the ArrayLists (Both Boots and Users) In depth description below...
/* Reads information from a file
            - stores first line as number of books in the library. - use this to determine the number of times you loop through and read books
            - every two lines is information about a book
                        + first line = book title,
                        + second line = Author's name
            - Number then appears to show the number of library users - use this to determine the number of times you loop through and read user data
            - Each line is then the user information.
         */
    public void readText() throws FileNotFoundException {
        SortedArrayList sal = new SortedArrayList();

        Book b = new Book();
        User u = new User();
        Scanner inFile = new Scanner(new FileReader("libraryRead.txt"));
        int numberOfBooks = Integer.valueOf(inFile.nextLine());
        for(int i=0; i<numberOfBooks; i++) {
            String line1 = inFile.nextLine();
            String line2 = inFile.nextLine();
            String[] authorName = new String[2]; //index 0 firstname, index 1 = surname
            if (line2.contains(".")){
                authorName[1] = line2.substring(line2.lastIndexOf(".") + 2);
                authorName[0] = line2.substring(0, line2.lastIndexOf(".") + 1);
            } else {
                String[] name = line2.split(" ");
                authorName[0] = name[0];
                authorName[1] = name[1];
            }
            Book bookToAdd = new Book(line1, authorName[1], authorName[0]);

            bookList = sal.sortList(bookList, bookToAdd);


        }
        // Read the users
        int numberOfUsers = Integer.valueOf(inFile.nextLine());
        for(int i = 0; i < numberOfUsers; i++){
            String[] userInfo = inFile.nextLine().split(" ");
            User user = new User(userInfo[0], userInfo[1]);

            userList = sal.sortList(userList, user);

        }
    }

    // starts menu that leads to various functions.
    public void menu() throws FileNotFoundException {
        PrintWriter writer = new PrintWriter("libraryInformation.txt");
        printMenu();
        char ch = k.next().charAt(0);
        while(ch!= 'f') {
            switch(ch) {
                case 'b':
                    System.out.println("Displaying all the books in the library: ");
                    printAllBooks();
                    break;
                case 'u':
                    System.out.println("Displaying all the users registered to the library: ");
                    printAllUsers();
                    break;
                case 'i':
                    User user1 = askUser();
                    optionI(writer, user1);
                    break;
                case 'r':
                    System.out.println("Updating the stored data of a book being returned by a user: ");
                    User user2 = askUser();
                    optionR(user2);
                    break;
                default:
                    System.out.println("Invalid entry, try again.");
            }
            printMenu();
            ch = k.next().charAt(0);
            k.nextLine();
        }
        writer.close();
    }

    // prints menu
    private static void printMenu() {
        System.out.println("<><><><><><><><><><><><><><><><><><><><><><><><><>");
        System.out.println("                    MENU");
        System.out.println("f - finish");
        System.out.println("b - display all books");
        System.out.println("u - display all users");
        System.out.println("i - update stored data of a book issued to a user");
        System.out.println("r - update stored data of a book return by a user");
        System.out.println("<><><><><><><><><><><><><><><><><><><><><><><><><>");
        System.out.println("        Type a letter and press Enter");
    }

    // Prints to console and also to the file ----- NEED TO TEST THIS LOL


//Methods performed on User Objects:

// Checking if the user is a valid user that is able to take out a book.
    public User askUser() {
        System.out.println("Please enter the following details about the user: ");
        System.out.println("Their first name: ");
        String userFirstName = new Scanner(System.in).nextLine();

        System.out.println("Their last name; ");
        String userLastName = new Scanner(System.in).nextLine();

        User user = new User(userFirstName, userLastName);
        return user;
    }
    // Checks if a user is a valid user using the equals method.
    public boolean checkValidUser(User user) {
        boolean exists = false;
        for (User u: userList) {
            if(u.equals(user)) {
                exists =  true;
            }
        }
        return exists;
    }

    // Returns user - will return null if there is no user that matches the search but that is why it will be used with the above valid search method.
    public User getValidUser(User user) {
        User toReturn = null;
        for (User u: userList) {
            if(u.equals(user)) {
                toReturn = u;
            }
        }
        return toReturn;
    }

    // Returns number of books the user currently has
    public int getNumberOfBooks(User user) {
        int x = 0;
        for (User u: userList) {
            if(u.equals(user)) {
                x = u.getNumberOfBooksLoaned();
            }
        }
        return x;
    }

    // Increments/decrements the number of books the User has
    public void increaseCount(User user) {
        for (User u: userList) {
            if (u.equals(user)) {
                u.raiseNumberOfBooksLoaned();
            }
        }
    }
    public void decreaseCount(User user) {
        for (User u: userList) {
            if (u.equals(user)) {
                u.lowerNumberOfBooksLoaned();
            }
        }
    }

    // Sends a reminder to the user holding the book
    public String sendReturnReminder(Book book) {
        String reminderTo = null;

        for (Book b : bookList) {
            if(b.equals(book)) {
                reminderTo = b.reminder();
            }
        }
        return reminderTo;
    }

//Methods performed on Book Objects:
    // Checks if a book is a valid user using the equals method.
    public Book askBook() {
        // Creates an instance of a book an looks to see if that book is in the ArrayList.
        // If it is, that book in the arrayList is modified using the setter methods
        System.out.println("Updating the stored data of a book to be issued to a user: ");
        System.out.println("Please enter the following: ");

        System.out.println("Title of the book: ");
        String bookTitle = new Scanner(System.in).nextLine();

        System.out.println("First name of the author... ");
        String authorFirstName = new Scanner(System.in).nextLine();

        System.out.println("Last name of the author...");
        String authorLastName = new Scanner(System.in).nextLine();

        Book book = new Book(bookTitle, authorLastName, authorFirstName);
        return book;
    }
    public boolean checkValidBook(Book book) {
        boolean exists = false;
        for (Book u: bookList) {
            if(u.equals(book)) {
                exists =  true;
            }
        }
        return exists;
    }

    // Returns book - will return null if there is no book that matches the search but that is why it will be used with the above valid search method.
    public Book getValidBook(Book book) {
        Book toReturn = null;
        for (Book b: bookList) {
            if(b.equals(book)) {
                toReturn = b;
            }
        }
        return toReturn;
    }

    // Checks whether a book is on loan
    public boolean bookOnLoan(Book book) {
        boolean onLoan = false;
        for (Book b: bookList) {
            if(b.equals(book)) {
                onLoan = b.isOnLoan();
            }
        }
        return onLoan;
    }

    // Book is modified by changing loanee to the user.
    public Book loanBookToUser(Book book, User user) {
        Book toReturn = null;
        for (Book b: bookList) {
            if(b.equals(book)) {
                b.setOnLoan(true);
                b.setLoanee(user);
                toReturn = b;
            }
        }
        return toReturn;
    }

    //Book in the arrayList is modified, removing the user and changing the boolean onLoan value to false
    public void returnedByUser(Book book) {
        for(Book b: bookList) {
            if(b.equals(book)) {
                b.setOnLoan(false);
                for (User u: userList) {
                    if (b.getLoanee().equals(u)) {
                        u.lowerNumberOfBooksLoaned();
                    }
                }
                b.setLoanee(null);
            }
        }
    }

// Menu Options
    // Prints all the books to the console. - This is option "b"
    public void printAllBooks() {
        for(Book b: bookList) {
            System.out.println(b.toString());
            System.out.println();
        }
    }

    // Prints all the users to the console. - This is option "u"
    public void printAllUsers() {
        for(User u: userList) {
            System.out.println(u.toString());
            System.out.println();
        }
    }

    //Option-i - this is when the librarian wants to lend a book to a user...
    public void optionI(PrintWriter writer, User user) {
        if(checkValidUser(user)) {
            Book book = askBook();
            // checks if the book is a valid book, if not, the function ends here and they return to the menu
            if(checkValidBook(book)) {
                // checks if the book is on loan - if it is, then a reminder is printed to the writer
                if (!bookOnLoan(book)) {
                    // checks if the user can borrow anymore books - if not then the function ends here
                    if(getNumberOfBooks(user) <3) {
                        loanBookToUser(book, user);
                        increaseCount(user);
                        System.out.println("The book has been issued successfully.");
                    }
                    else {
                        System.out.println("This user has already reach the maximum number of books they can borrow.");
                    }
                }
                else {
                    String reminder = sendReturnReminder(book);
                    writer.println(reminder);
                    System.out.println("The book is currently on loan.");
                    System.out.println("A reminder to return the book has been sent to the current holder.");
                }
            } else {
                System.out.println("The book is not a valid book on our system.");
            }
        } else {
            System.out.println("The user is not registered on our system and is therefore unable to loan a book. ");
        }
    }

    //Option-r - this is when a user returns a book to the library
    public void optionR(User user) {
        if(checkValidUser(user)) {
            Book book = askBook();
            if (checkValidBook(book)) {
                if (bookOnLoan(book)) {
                    if(user.equals(getValidBook(book).getLoanee())) {
                        returnedByUser(book);
                        System.out.println("The book has been successfully returned.");
                    } else {
                        System.out.println("This person did not borrow this particular book.");
                    }

                } else {
                    System.out.println("This book is not on loan and therefore cannot be returned.");
                }
            } else {
                System.out.println("This is not a book on our system.");
            }
        } else {
            System.out.println("This is not a registered User.");
        }
    }

//Main Method <><><><><>><><><><>><><><><>><><><><>><><><><>><><><><>><><><><>><><><><>><><><><>><><><><>><><><><>><><>
    public static void main(String[] args) throws FileNotFoundException {
        LibraryMain lm = new LibraryMain();
        lm.readText();
        lm.menu();
    }
}


