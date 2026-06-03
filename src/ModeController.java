public class ModeController {

    private ProductManager manager;

    public ModeController(ProductManager manager) {
        this.manager = manager;
    }

    public ProductManager getManager() {
        return manager;
    }

    public void startAdmin() {
        Admin admin = new Admin(manager);

    }

    public void startNutzer(float guthaben) {
        Nutzer nutzer = new Nutzer(guthaben);

    }
}

