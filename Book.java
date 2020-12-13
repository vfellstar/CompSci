import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

public class Book implements Comparable<Book> {
    private String title;
    private String authorLN;
    private String authorFN;
    private boolean onLoan;
    private User loanee;

    public Book() {

    }
    public Book(String title, String authorLN, String authorFN) {
        this.title = title;
        this.authorLN = authorLN;
        this.authorFN = authorFN;
        this.onLoan = false;
    }
    public Book(String title, String authorLN, String authorFN, boolean onLoan, User loanee) {
        this.title = title;
        this.authorLN = authorLN;
        this.authorFN = authorFN;
        this.onLoan = onLoan;
        this.loanee = loanee;
    }

// getters

    public User getLoanee() {
        return loanee;
    }

    public boolean isOnLoan() {
        return onLoan;
    }

    public String reminder() {
        return "Dear " + loanee.getFirstname() + " " + loanee.getLastName() + ",\n" +
                "The copy of the book \"" + title + "\" you are holding has been requested by another customer. " +
                "Please return them as soon as possible." + "\n" +
                "Many thanks," + "\n" +
                "Awesome library inc." + "\n\n";
    }
// setters

    public void setOnLoan(boolean onLoan) {
        this.onLoan = onLoan;
    }

    public void setLoanee(User loanee) {
        this.loanee = loanee;
    }


// methods
    // compares lastname, firstname, number of books loaned in that order
    @Override
    public int compareTo(Book b) {
        int lnCompare = authorLN.compareTo(b.authorLN);
        if(lnCompare != 0) return lnCompare;
        int titleCompare = title.compareTo(b.title);
        if(titleCompare !=0) return title.compareTo(b.title);
        else return authorFN.compareTo(b.authorFN);
    }

    // creates a hashcode for each object based on their fields
    @Override
    public int hashCode() {
        return Objects.hash(title, authorFN, authorLN);
    }

    @Override
    public boolean equals(Object o) {
        if (hashCode() == o.hashCode()) return true;
        Book toCompare = (Book) o;
        if (this.authorLN.equals(toCompare.authorLN) && this.authorFN.equals(toCompare.authorFN) && this.title.equals(toCompare.title)) return true;
        else return false;
    }
    // prints books to string
    @Override
    public String toString() {
        String string =  "Author: " + authorLN +
                ", " + authorFN + "\n" +
                "Title: " + title + "\n" +
                "On loan: " + onLoan + "\n"
                ;
        if (onLoan) string += "Currently with: " + loanee.getLastName() + ", " + loanee.getFirstname();
        return string;
    }

}
