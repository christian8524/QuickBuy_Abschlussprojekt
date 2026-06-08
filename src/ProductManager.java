import java.util.ArrayList;
import java.util.Iterator;

public class ProductManager {

    private ArrayList<Product> products = new ArrayList<>();

    public void createProduct(String name, float preis, int id) {
        products.add(new Product(name, preis, products.size()+1));
    }

    public ArrayList<Product> getAllProducts() {
        return products;
    }

    public boolean updateProduct(int id, String newName, float newPrice) {
        for (Product p : products) {
            if (p.getId() == id) {
                p.setName(newName);
                p.setPreis(newPrice);
                return true;
            }
        }
        return false;
    }

    public boolean deleteProduct(int id) {
        Iterator<Product> it = products.iterator();

        while (it.hasNext()) {
            if (it.next().getId() == id) {
                it.remove();
                return true;
            }
        }
        return false;

    }

    public boolean isEmpty() {
        return products.isEmpty();
    }
}

