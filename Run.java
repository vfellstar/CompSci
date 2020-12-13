import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Scanner;

public class Run extends ArrayList {


    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
        // Reading from a file -- Has to be done before menu appears so there are books and users from the start.
                //All good <><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><>
        LibraryMain lm = new LibraryMain();
        lm.readText();
        System.out.println(lm.bookList.toArray().length);
        lm.printAllBooks();
        System.out.println(lm.userList.toArray().length);

        System.out.println("<><><><><><><><><><><><><><><><>");

        System.out.println("<><><><><><><><><><><><><><><><>");
        // Writing to a file:
        PrintWriter writer = new PrintWriter("libraryInformation.txt");
        writer.println("The first line");
        writer.println("The second line");
        writer.close();

    }

    // methods for running
    // switch menu



    // Prints the menu



}
