import java.util.ArrayList;
import java.util.Objects;
// When overriding .equals make sure to alter hashcode
public class User implements Comparable<User> {
    private String firstName;
    private String lastName;
    private int numberOfBooksLoaned;

    // creators
    User() {
    }

    User(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.numberOfBooksLoaned = 0;
    }

    User(String firstname, String lastName, int numberOfBooksLoaned) {
        this.firstName = firstname;
        this.lastName = lastName;
        this.numberOfBooksLoaned = numberOfBooksLoaned;
    }

    // getters
    public String getFirstname() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getNumberOfBooksLoaned() {
        return numberOfBooksLoaned;
    }


    // setters

    public void raiseNumberOfBooksLoaned() {
        this.numberOfBooksLoaned = numberOfBooksLoaned +1;
    }

    public void lowerNumberOfBooksLoaned() {
        this.numberOfBooksLoaned = numberOfBooksLoaned -1;
    }


// Methods

    // Override methods
    @Override
    public String toString() {
        return "Lastname: " + lastName + "\n" +
                "Firstname: " + firstName + "\n" +
                "Number of books currently on loan: " + numberOfBooksLoaned;
    }
    @Override
    public boolean equals(Object o) {
        User toCompare = (User) o;
        if (hashCode() == o.hashCode()) return true;
        if (this.lastName.equals(toCompare.lastName) && this.firstName.equals(toCompare.firstName)) return true;
        else return false;
    }
    //compares lastname, firstname, number of books loaned in that order
    @Override
    public int compareTo(User u) {
        if (this.lastName.compareTo(u.lastName)!= 0) return this.lastName.compareTo(u.lastName);
        else return this.firstName.compareTo(u.firstName);
    }
    // creates a hashcode for each object based on their fields
    @Override
    public int hashCode() {
        return Objects.hash(lastName, firstName);
    }

     /* This method takes an arraylist, returns the minimal object on the list.
        At each turn of the loop we compare the elements with the element which is minimal so far */


}
