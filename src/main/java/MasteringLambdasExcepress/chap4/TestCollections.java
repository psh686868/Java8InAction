package MasteringLambdasExcepress.chap4;

import MasteringLambdasExcepress.Books;
import MasteringLambdasExcepress.chap3.Book;
import MasteringLambdasExcepress.chap3.Topic;

import java.time.Year;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Create by psh
 * Date: 2017/11/6
 */
public class TestCollections {
    public static void main(String[] args) {
        List<Book> books = Books.getBooks();
        TreeMap<String, Year> collect3 = books.stream()
                .collect(Collectors.toMap(Book::getTitle, Book::getPubDate, (x, y) -> x.isAfter(y) ? x : y, TreeMap::new));

        Map<Topic, List<Book>> collect4 = books.stream()
                .collect(Collectors.groupingBy(Book::getTopic, Collectors.toList()));

        Stream<Map.Entry<Topic, Long>> entryStream = books.stream()
                .collect(Collectors.groupingBy(Book::getTopic, Collectors.counting()))
                .entrySet().stream();
        Optional<Topic> topic = entryStream.max(Map.Entry.comparingByKey())
                .map(Map.Entry::getKey);
        topic.orElse(Topic.PROGRAMMING);


    }
}
