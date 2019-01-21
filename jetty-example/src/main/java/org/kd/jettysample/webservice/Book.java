package org.kd.jettysample.webservice;

public class Book {

    private long bookId;
    private String bookName;
    private String isn;
    private double price;
    private String author;

    public long getBookId() {
        return bookId;
    }

    public void setBookId(long bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getIsn() {
        return isn;
    }

    public void setIsn(String isn) {
        this.isn = isn;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
