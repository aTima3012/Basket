import java.io.File;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Basket basket;
        Product[] products = new Product[4];
        products[0] = new Product("Икра", 6000);
        products[1] = new Product("Персики", 350);
        products[2] = new Product("Чай", 200);
        products[3] = new Product("Огурцы", 350);

        File file = new File("basket.txt");

        if (file.exists()) {
            basket = Basket.loadFromTxtFile(file);
        } else {
            basket = new Basket(products);
        }
        System.out.println("Список товаров для покупки");
        for (int i = 0; i < products.length; i++) {
            System.out.println((i + 1) + ". " + products[i].getName() + " " + products[i].getPrice() + " руб");
        }
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Выберите товар и количество или введите `end`");
            String input = scanner.nextLine();
            if ("end".equals(input)) {
                basket.printCart();
                break;
            }
            String[] parts = input.split(" ");
            basket.addToCart(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));
            basket.saveTxt(file);
        }
    }
}


