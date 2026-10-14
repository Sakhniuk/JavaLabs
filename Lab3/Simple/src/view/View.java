package view;

import model.Book;
import java.util.List;

public class View {

    public void printHeader() {
        System.out.printf("%-25s | %-18s | %-15s | %4s | %6s | %9s%n",
                "Назва", "Автор", "Видавництво", "Рік", "Стор.", "Ціна");
    }

    public void printBooks(List<Book> books) {
        if (books == null || books.isEmpty()) {
            System.out.println(">>> Книг не знайдено.");
            return;
        }
        printHeader();
        for (Book b : books) {
            System.out.println(b);
        }
    }

    public void printMessage(String msg) {
        System.out.println(msg);
    }

    public void printMenu() {
        System.out.println("\n МЕНЮ ");
        System.out.println("1. Показати всі книги");
        System.out.println("2. Книги заданого автора");
        System.out.println("3. Книги заданого видавництва");
        System.out.println("4. Книги, видані пізніше вказаного року");
        System.out.println("5. Відсортувати книги за видавництвами");
        System.out.println("0. Вихід");
        System.out.print("Ваш вибір: ");
    }
}