package org.kd.jettysample.webservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "book")
public class BookDTO {

    @XmlElement
    private long bookId;
    @XmlElement
    private String bookName;
    @XmlElement
    private String isn;
    @XmlElement
    private double price;

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

    @Override
    public String toString() {
        return "BookDTO{" +
                "bookId=" + bookId +
                ", bookName='" + bookName + '\'' +
                ", isn='" + isn + '\'' +
                ", price=" + price +
                '}';
    }
}
