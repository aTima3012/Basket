import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Basket {
    protected Product[] cart;
    public Basket(Product[] products) {
        this.cart = products;
    }

    public void addToCart(int productNum, int amount) {
        this.cart[productNum - 1].setAmount(this.cart[productNum - 1].getAmount() + amount);
    }

    public void printCart() {
        int sumProducts = 0;
        System.out.println("Ваша корзина:");
        for (int i = 0; i < cart.length; i++) {
            if (cart[i].getAmount() != 0) {
                System.out.println((i + 1) + ". " + cart[i].getName() + " " + cart[i].getAmount() + " " + cart[i].getPrice() + " руб/шт " + cart[i].getAmount() * cart[i].getPrice() + " руб в сумме");
                sumProducts += cart[i].getAmount() * cart[i].getPrice();
            }
        }
        System.out.println("Итого " + sumProducts + " руб");
    }

    public static Basket loadFromTxtFile(File file) {
        List<Product> cartFromSavedFile = new ArrayList<>();
        String[] parts;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String s;
            while ((s = br.readLine()) != null) {
                parts = s.trim().split(" ");
                cartFromSavedFile.add(new Product(parts[0], Integer.parseInt(parts[1]), Integer.parseInt(parts[2])));
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return new Basket(cartFromSavedFile.toArray(Product[]::new));
    }


    public void saveTxt(File file) {
        try (PrintWriter out = new PrintWriter(file)) {
            for (Product product : cart) {
                out.println(product.getName() + " " + product.getPrice() + " " + product.getAmount());
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

}