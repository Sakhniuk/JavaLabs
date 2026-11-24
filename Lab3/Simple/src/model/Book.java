package model;

public class Book {
    private String title;
    private String author;
    private String publisher;
    private int year;
    private int pages;
    private double price;

    public Book(String title, String author, String publisher,
                int year, int pages, double price) {
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.year = year;
        this.pages = pages;
        this.price = price;
    }

    //деякі поля не ініціалізовані,але мають бути для повноти коду(я так думаю)
    public String getTitle()
        { return title; }

    public String getAuthor()
        { return author; }

    public String getPublisher()
        { return publisher; }

    public int getYear()
        { return year; }

    public int getPages()
        { return pages; }

    public double getPrice()
        { return price; }

    @Override
    public String toString() {
        return String.format("%-25s | %-18s | %-15s | %4d | %4d ст. | %7.2f грн",
                title, author, publisher, year, pages, price);
    }
}