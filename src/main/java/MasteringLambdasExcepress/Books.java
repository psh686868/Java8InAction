package MasteringLambdasExcepress;

import MasteringLambdasExcepress.chap3.Book;
import MasteringLambdasExcepress.chap3.Topic;

import java.time.Year;
import java.util.Arrays;
import java.util.List;

/**
 * Create by psh
 * Date: 2017/11/6
 */
public class Books {
    public static List<Book> getBooks () {
        Book nails = new Book(("Fundamentals of Chinese Figernail Image"),
                Arrays.asList("Li","Fu","Li"),
                new int[256],
                Topic.HISTORY,
                Year.of(2014),
                25.3);

        Book dragon = new Book(("Lord of the Rings"),
                Arrays.asList("Aho","Lam","Sethi","Ullman"),
                new int[] {478},
                Topic.HISTORY,
                Year.of(2006),
                19.8);

        Book voss = new Book(("Voss"),
                Arrays.asList("Patrick White"),
                new int[] {478},
                Topic.HISTORY,
                Year.of(1957),
                19.8);

        Book lotr = new Book(("Lotr of the Rings"),
                Arrays.asList("Tolkien"),
                new int[] {513,416,624},
                Topic.HISTORY,
                Year.of(1957),
                23.0);

        List<Book> books = Arrays.asList(nails, dragon, voss, lotr);
        return books;
    }
}
