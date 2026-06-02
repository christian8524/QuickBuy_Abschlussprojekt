import java.util.ArrayList;

public class Admin {

    private ArrayList<Product> product = new ArrayList<>();

    public void createProduct(String name,float preis,int id){

        product.add(new Product(name,preis,id));

    }

    public ArrayList<Product>getAllProducts(){

        return product;
    }

    public void updateProduct(int id,String newName,float newPrice){


        for (Product p : product){

            if(p.getId()==id){
                p.setName(newName);
                p.setPreis(newPrice);
            }

        }

    }

    public void deleteProduct(int id){

        for (Product p : product) {
            if (p.getId() == id) {
                product.remove(p);
            }
        }

    }

    public boolean isEmpty(){

        return product.isEmpty();

    }

}
