package MasteringLambdasExcepress.chap3;

import java.time.Year;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Create by psh
 * Date: 2017/11/3
 */
public class BookMain {
    public static void main(String[] args) {
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

        List<String> collect = books.stream()
                .flatMap(book -> book.getAuthors().stream())
                .collect(Collectors.toList());

        List<Stream<String>> collect1 = books.stream()
                .map(book -> book.getAuthors().stream())
                .collect(Collectors.toList());

        List<List<String>> collect2 = books.stream()
                .map(book -> book.getAuthors())
                .collect(Collectors.toList());

        TreeMap<String, Year> collect3 = books.stream()
                .collect(Collectors.toMap(Book::getTitle, Book::getPubDate, (x, y) -> x.isAfter(y) ? x : y, TreeMap::new));

        Map<Topic, List<Book>> collect4 = books.stream()
                .collect(Collectors.groupingBy(Book::getTopic, Collectors.toList()));


    }
}
