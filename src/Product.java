public class Product {

    private String name;
    private float preis;
    private int id;

    public Product(String name, float preis, int id) {
        this.name = name;
        this.preis = preis;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public float getPreis() {
        return preis;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPreis(float preis) {
        this.preis = preis;
    }

    public void setId(int id) {
        this.id = id;
    }
}
