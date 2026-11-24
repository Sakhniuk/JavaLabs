package controller;

import model.Book;
import view.View;

import java.util.*;

public class Controller {
    private List<Book> books;
    private final View view;
    private final Random random = new Random();

    private final String[] preparedAuthors = {
            "Тарас Шевченко",
            "Іван Франко",
            "Леся Українка",
            "Олесь Гончар",
            "Ліна Костенко",
            "Михайло Коцюбинський",
            "Іван Багряний",
            "Панас Мирний"
    };

    private final String[] preparedPublishers = {
            "Основа",
            "Дніпро",
            "Смолоскип",
            "Рух",
            "Наукова думка",
            "Укрвидав"
    };

    private final int[] preparedYears = { 1900, 1920, 1940, 1960, 1970 };

    public Controller(List<Book> books, View view) {
        this.books = books;
        this.view = view;
    }

    public void run() {
        Scanner sc = new Scanner(System.in);
        boolean running = true;

        view.printMessage("\n ВИХІДНИЙ НАБІР КНИГ:");
        view.printBooks(books);

        while (running) {
            view.printMenu();
            String choice = sc.nextLine().trim();

            switch (choice) {
                case "1":
                    view.printMessage("\n Всі книги:");
                    view.printBooks(books);
                    break;

                case "2": {

                    String author = randomAuthor();
                    view.printMessage("\n Випадково обраний автор: " + author);
                    view.printBooks(findByAuthor(author));
                    break;
                }

                case "3": {
                    String publisher = randomPublisher();
                    view.printMessage("\n Випадково обране видавництво: " + publisher);
                    view.printBooks(findByPublisher(publisher));
                    break;
                }

                case "4": {
                    int year = randomYear();
                    view.printMessage("\n Випадково обраний рік: " + year);
                    view.printBooks(findAfterYear(year));
                    break;
                }

                case "5":
                    sortByPublisher();
                    view.printMessage("\n Книги, відсортовані за видавництвами:");
                    view.printBooks(books);
                    break;

                case "0":
                    running = false;
                    view.printMessage("Завершення роботи...");
                    break;

                default:
                    view.printMessage(" Невірний вибір. Спробуйте ще раз.");
            }
        }
        sc.close();
    }

    private String randomAuthor() {
        return preparedAuthors[random.nextInt(preparedAuthors.length)];
    }

    private String randomPublisher() {
        return preparedPublishers[random.nextInt(preparedPublishers.length)];
    }

    private int randomYear() {
        return preparedYears[random.nextInt(preparedYears.length)];
    }


    public List<Book> findByAuthor(String author) {
        List<Book> result = new ArrayList<>();
        for (Book b : books) {
            if (b.getAuthor().equalsIgnoreCase(author)) {
                result.add(b);
            }
        }
        return result;
    }


    public List<Book> findByPublisher(String publisher) {
        List<Book> result = new ArrayList<>();
        for (Book b : books) {
            if (b.getPublisher().equalsIgnoreCase(publisher)) {
                result.add(b);
            }
        }
        return result;
    }


    public List<Book> findAfterYear(int year) {
        List<Book> result = new ArrayList<>();
        for (Book b : books) {
            if (b.getYear() > year) {
                result.add(b);
            }
        }
        return result;
    }


    public void sortByPublisher() {
        books.sort(new Comparator<Book>() {
            @Override
            public int compare(Book b1, Book b2) {
                return b1.getPublisher().compareToIgnoreCase(b2.getPublisher());
            }
        });
    }
}