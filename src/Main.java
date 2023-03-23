import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.util.Scanner;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Basket basket;

        Product[] products = new Product[4];
        products[0] = new Product("Икра", 6000);
        products[1] = new Product("Персики", 350);
        products[2] = new Product("Чай", 200);
        products[3] = new Product("Огурцы", 350);

        File file = new File("basket.json");
        File logFile = new File("log.csv");

        if (file.exists()) {
            basket = Basket.loadFromJsonFile(file);
        } else {
            basket = new Basket(products);
        }
        System.out.println("Список возможных товаров для покупки");
        for (int i = 0; i < products.length; i++) {
            System.out.println((i + 1) + ". " + products[i].getName() + " " + products[i].getPrice() + " руб/шт");
        }
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Выберите товар и количество или введите `end`");
            String input = scanner.nextLine();
            if ("end".equals(input)) {
                basket.printCart();
                try {
                    basket.clientLog.exportAsCSV(logFile);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                break;
            }
            String[] parts = input.split(" ");
            basket.addToCart(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));
            basket.saveJson(file);
        }
    }
}