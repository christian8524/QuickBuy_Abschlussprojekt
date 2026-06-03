import java.util.ArrayList;

public class Admin {



        private ProductManager manager;

        public Admin(ProductManager manager) {
            this.manager = manager;
        }

        public void createProduct(String name, float preis, int id) {
            manager.createProduct(name, preis, id);
        }

        public void updateProduct(int id, String newName, float newPrice) {
            manager.updateProduct(id, newName, newPrice);
        }

        public void deleteProduct(int id) {
            manager.deleteProduct(id);
        }

        public ArrayList<Product> getAllProducts() {
            return manager.getAllProducts();
        }
    }



