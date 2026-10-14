import controller.Controller;
import model.Book;
import view.View;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Book> books = new ArrayList<>();
        books.add(new Book("Кобзар", "Тарас Шевченко", "Основа", 1840, 320, 250.00));
        books.add(new Book("Мина Мазайло", "Микола Куліш", "Рух", 1929, 180, 190.50));
        books.add(new Book("Місто", "Валер'ян Підмогильний", "Смолоскип", 1928, 400, 320.00));
        books.add(new Book("Тигролови", "Іван Багряний", "Укрвидав", 1944, 350, 275.00));
        books.add(new Book("Собор", "Олесь Гончар", "Дніпро", 1968, 450, 410.00));
        books.add(new Book("Intermezzo", "Михайло Коцюбинський", "Рух", 1908, 150, 170.00));
        books.add(new Book("Маруся Чурай", "Ліна Костенко", "Радянський письменник", 1979, 280, 360.00));
        books.add(new Book("Кайдашева сім'я", "Іван Нечуй-Левицький", "Наукова думка", 1879, 240, 210.00));
        books.add(new Book("Хіба ревуть воли...", "Панас Мирний", "Дніпро", 1875, 500, 380.00));
        books.add(new Book("Захар Беркут", "Іван Франко", "Основа", 1883, 300, 240.00));
        books.add(new Book("Лісова пісня", "Леся Українка", "Смолоскип", 1911, 200, 195.00));
        books.add(new Book("Мисливські усмішки", "Остап Вишня", "Наукова думка", 1958, 260, 220.00));

        View view = new View();
        Controller controller = new Controller(books, view);
        controller.run();
    }
}