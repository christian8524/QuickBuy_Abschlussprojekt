import java.util.ArrayList;

public class Nutzer {

    private float guthaben;
    private ArrayList<Product> warenkorb = new ArrayList<>();


    public Nutzer(float guthaben) {
        this.guthaben = guthaben;
    }

    public float getGuthaben() {
        return guthaben;
    }

    public void setGuthaben(float guthaben) {
        this.guthaben = guthaben;
    }
    public void addToCart(Product p) {
        warenkorb.add(p);
    }

    public float getTotal() {
        float sum = 0;
        for (Product p : warenkorb) {
            sum += p.getPreis();
        }
        return sum;
    }
    public void clearCart() {
        warenkorb.clear();
    }
    public boolean pay() {
        float total = getTotal();

        if (total > guthaben) {
            System.err.println("Nicht genug Guthaben");
            return false;
        }

        guthaben -= total;
        clearCart();
        return true;
    }
    public ArrayList<Product> getWarenkorb() {
        return warenkorb;
    }
}
