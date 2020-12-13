import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class methodTesting {
    private final LibraryMain lm;
    methodTesting() {
        this.lm = new LibraryMain();
    }
    public static void main(String[] args) throws FileNotFoundException {
        PrintWriter writer = new PrintWriter("libraryInformation.txt");
        writer.println("The first line");
        writer.println("The second line");


        // Testing SortedArrayList with Users
        System.out.println("<><><><><><><><><><><><><><><><><><><><><><><><><>");
        System.out.println("Testing the add books method");
        TestUsersWithSortedArrayList();
        TestBooksWithSortedArrayList();
        System.out.println();
        System.out.println("<><><><><><><><><><><><><><><><><><><><><><><><><>");
        TestValidUserSearch();
        System.out.println("<><><><><><><><><><><><><><><><><><><><><><><><><>");
        checkValidBook();
        System.out.println("<><><><><><><><><><><><><><><><><><><><><><><><><>");
        testingOptionI(writer);


        System.out.println("<><><><><><><><><><><><><><><><><><><><><><><><><>");
        testingOptionRFunctions();
        writer.close();
    }

    // Testing SortedArrayList with Users
    public static void TestUsersWithSortedArrayList(){
        ArrayList<User> userList = new ArrayList<>();
        userList.add(new User("Vincent", "A", 3));
        userList.add(new User("Rhian", "B", 3));
        userList.add(new User("Tiger", "D", 3));

        System.out.println("<><><><><><><><> Original List: <><><><><><><><>");
        for(Object a: userList) {
            System.out.println(a.toString());
            System.out.println();
        }

        User user1 = new User("Merlin", "C", 3);
        System.out.println("<><><><><><><><> After adding to List: <><><><><><><><>");
        ArrayList<User> newList = SortedArrayList.sortList(userList, user1);
        for (User u: newList) {
            System.out.println(u.toString());
        }
    }

    // Testing SortedArrayList with Books
    public static void TestBooksWithSortedArrayList(){
        ArrayList<Book> bookList = new ArrayList<>();
        User user1 = new User("Vincent", "A", 3);
        Book b1 = new Book("A lullaby to Books", "Afro", "Jack", false, user1);
        Book b2 = new Book("Book about Books", "Bookington", "Brock", false, user1);
        Book b3 = new Book("Drool Books", "Derp", "Feis", false, user1);
        bookList.add(b1);
        bookList.add(b2);
        bookList.add(b3);
        System.out.println("<><><><><><><><> Original List: <><><><><><><><>");
        for(Object a: bookList) {
            System.out.println(a.toString());
            System.out.println();
        }

        Book b4 = new Book("Cool Books", "Crey", "Christian", true, user1);
        System.out.println("<><><><><><><><> After adding to List: <><><><><><><><>");
        ArrayList<Book> newBookList = SortedArrayList.sortList(bookList, b4);
        for (Book u: newBookList) {
            System.out.println(u.toString());
            System.out.println();
        }
    }

    // Tested validUser Methods + Return Valid User method - also tests if equals methods are correct.
    public static void TestValidUserSearch() throws FileNotFoundException {
        LibraryMain lm = new LibraryMain();
        lm.readText();

        System.out.println("This doesn't exist in LibraryMain userList--- should return \"false\".");
        System.out.println(lm.checkValidUser(new User("Tiger", "D", 3)));

        System.out.println();
        System.out.println("This is what's in the ArrayList");
        System.out.println(lm.userList.toArray().length);
        for(User u: lm.userList) {
            System.out.println(u.toString());
            System.out.println();
        }
        System.out.println("This exist in LibraryMain userList--- should return \"true\".");
        User userToCheck = new User("Anna", "Smith");
        System.out.println(userToCheck.toString());
        System.out.println(lm.checkValidUser(userToCheck));
        System.out.println("<><><><><><><><><><><><><><><><><><><><><><><><><>");
        System.out.println("Checking the return method...");
        User userToCheck2 = new User("Anna", "Smith");
        User gotUser = null;
        if (lm.checkValidUser(userToCheck2)) {
            gotUser = lm.getValidUser(userToCheck2);
        }
        System.out.println(gotUser.toString());
    }

    // Tested validBook Method - also tests if equals methods are correct.
    public static void checkValidBook() throws FileNotFoundException {
        LibraryMain lm = new LibraryMain();
        lm.readText();

        System.out.println("This doesn't exist in LibraryMain bookList--- should return \"false\".");
        System.out.println(lm.checkValidBook(new Book("Book", "Check", "CheckSurname")));

        System.out.println();
        System.out.println("This is what's in the ArrayList");
        for(Book u: lm.bookList) {
            System.out.println(u.toString());
            System.out.println();
        }
        System.out.println("This exist in LibraryMain userList--- should return \"true\".");
        Book bookToCheck = new Book("Java Gently", "Bishop", "Judith");
        System.out.println(bookToCheck.toString());
        System.out.println(lm.checkValidBook(bookToCheck));
        System.out.println("<><><><><><><><><><><><><><><><><><><><><><><><><>");
        System.out.println("Check the return method after a validity check...");
        Book gotBook = null;
        if(lm.checkValidBook(bookToCheck)) {
            gotBook = lm.getValidBook(bookToCheck);
        }
        System.out.println(gotBook.toString());
    }

    //  Testing OptionI - to see if it is able to change the properties of books in the ArrayList
    public static void testingOptionI(PrintWriter writer) throws FileNotFoundException {
        LibraryMain lm = new LibraryMain();
        lm.readText();
        User user = lm.askUser();
        lm.optionI(writer, user);
        lm.printAllBooks();
    }

    // Testing if incrementing and decrementing of number of books on loan for each user works (important for Option r)
    public static void testingOptionRFunctions() {
        LibraryMain lm = new LibraryMain();
        User user = lm.askUser();
        user.raiseNumberOfBooksLoaned();
        System.out.println(user.getNumberOfBooksLoaned());
        user.lowerNumberOfBooksLoaned();
        System.out.println(user.getNumberOfBooksLoaned());

    }

}
