import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
    // Метод для форматирования имени покупателя
    public static String viewOFname(String FirstName) {
        // Проверяем, что строка не пустая и не null
        if (FirstName == null || FirstName.isEmpty()) {
            throw new IllegalArgumentException("Неправильная форма заполнения имени."); // возвращаем сообщение об ошибке
        }
        // Проверяем, что строка состоит только из букв
        else if (!Pattern.matches("[a-zA-Zа-яА-Я]+", FirstName)) {
            throw new IllegalArgumentException("Неправильная форма заполнения имени."); // возвращаем сообщение об ошибке
        }
        // Преобразуем первый символ в верхний регистр
        char firstLetter = Character.toUpperCase(FirstName.charAt(0));
        // Преобразуем все остальные символы в нижний регистр
        String restOfName = FirstName.substring(1).toLowerCase();

        // Возвращаем отформатированное имя
        return firstLetter + restOfName;
    }
    // Метод для проверки валидности адреса эл. почты покупателя
    public static boolean isValidEmail(String Email) {
        String symbols = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(symbols);
        return pattern.matcher(Email).matches();
    }
    // Метод для форматирования номера телефона покупателя
    public static String viewOFphone(String Phone) {
        // Проверяем, что строка состоит только из цифр и их кол-во не более 10
        if (!Pattern.matches("\\d{10}", Phone)) {
            throw new IllegalArgumentException("Телефон должен состоять из цифр и их кол-во не должно быть больше 10.");
        }

        StringBuilder format = new StringBuilder();

        // Форматируем номере телефона
        for (int i = 0; i < Phone.length(); i++) {
            if (i == 0) {
                format.append("+7 (");
            } else if (i == 3) {
                format.append(") ");
            } else if (i == 6) {
                format.append("-");
            }
            else if (i == 8) {
                format.append("-");
            }
            format.append(Phone.charAt(i));
        }

        return format.toString(); // Возвращаем отформатированный номер телефона
    }
    public static void main(String[] args) {
        // Инициализируем массивы с информацией о фирмах и их коллекциях
        String[] firms = {"Rolex", "Vacheron Constantin", "Blancpain", "Chopard"};
        String[][] collections = {{"Rolex", "Datejust"}, {"Vacheron Constantin", "Traditionnelle"}, {"Blancpain", "MÉTIERS D'ART"}, {"Chopard", "IMPERIALE"}};

        // Создаём список для хранения информации о покупателях
        ArrayList<Customer> customers = new ArrayList<>();
        // Объект типа Scanner, с помощью которого получаем данные
        Scanner scanner = new Scanner(System.in);

        for (int i = 0; i < 3; i++) {
            // Выводим пользователю указания к действию
            System.out.println("Введите имя:");
            // Получаем данные от пользователя
            String FirstName = viewOFname(scanner.nextLine());
            System.out.println("Введите Email:");
            String Email = scanner.nextLine();
            // Вызываем метод для проверки валидности Email
            if (!isValidEmail(Email)) {
                throw new IllegalArgumentException("Неправильная форма заполнения адреса эл. почты.");
            }
            System.out.println("Введите свой телефон:");
            String Phone = viewOFphone(scanner.nextLine());

            int position = -1;
            while (position < 0 || position >= firms.length) {
                System.out.println("Часы в наличии (Брэнд-Коллекция):");
                // Вывод списка доступных товаров
                for (int j = 0; j < firms.length; j++) {
                    System.out.println((j + 1) + ". " + firms[j] + " - " + collections[j][1]);
                }

                System.out.println("Выбирите товар:");
                try {
                    position = Integer.parseInt(scanner.nextLine()) - 1;
                    if (firms.length < position || position < 0) {
                        System.out.println("Такого товара нет...");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Неверный ввод. Пишите цифрами!");
                }
            }

            int Quantity = -1;
            while (Quantity < 0) {
                System.out.println("Сколько вы хотите купить?:");
                try {
                    Quantity = Integer.parseInt(scanner.nextLine());
                    if (Quantity < 0) {
                        System.out.println("Кол-во товара не может быть отрицательным.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Неверный ввод. Пишите цифрами!");
                }
            }

            // Добавление полученной информации о покупателе и его заказе в список
            customers.add(new Customer(FirstName, Email, Phone, firms[position], collections[position][1], Quantity));
        }

        // Вывод информации о заказах
        for (Customer customer : customers) {
            System.out.println("Покупатель: " + customer.FirstName +
                    "\nEmail: " + customer.Email +
                    "\nТелефон: " + customer.Phone +
                    "\nТовар: " + customer.Collection + " - " + customer.Firm +
                    "\nКол-во: " + customer.Quantity + "\n");
        }
    }
}