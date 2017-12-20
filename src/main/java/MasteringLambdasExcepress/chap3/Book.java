package MasteringLambdasExcepress.chap3;

import java.time.Year;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Create by shitou
 * Date: 2017/11/3
 */
public class Book {
     private  String title;
     private List<String> authors;
     private int[] pageCounts;
     private Topic topic;
     private Year pubDate;
     private double height;

    public Book() {
    }

    public Book(String title, List<String> authors, int[] pageCounts, Topic topic, Year pubDate, double height) {
        this.title = title;
        this.authors = authors;
        this.pageCounts = pageCounts;
        this.topic = topic;
        this.pubDate = pubDate;
        this.height = height;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }

    public int[] getPageCounts() {
        return pageCounts;
    }

    public void setPageCounts(int[] pageCounts) {
        this.pageCounts = pageCounts;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public Year getPubDate() {
        return pubDate;
    }

    public void setPubDate(Year pubDate) {
        this.pubDate = pubDate;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", authors=" + authors +
                ", pageCounts=" + Arrays.toString(pageCounts) +
                ", topic=" + topic +
                ", pubDate=" + pubDate +
                ", height=" + height +
                '}';
    }
}
