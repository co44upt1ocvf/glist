// Класс для представления информации о клиенте
public class Customer {
    String FirstName;
    String Email;
    String Phone;
    String Firm;
    String Collection;
    int Quantity;

    // Конструктор созданного раннее класса
    public Customer(String FirstName, String Email, String Phone, String Firm, String Collection, int Quantity) {
        this.FirstName = FirstName;
        this.Email = Email;
        this.Phone = Phone;
        this.Firm = Firm;
        this.Collection = Collection;
        this.Quantity = Quantity;
    }
}
