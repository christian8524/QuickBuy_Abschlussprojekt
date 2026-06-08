public class ModeController {

    private ProductManager manager;

    public ModeController(ProductManager manager) {
        this.manager = manager;
    }

    public ProductManager getManager() {
        return manager;
    }

    public Admin startAdmin() {
        Admin admin = new Admin(manager);

        return admin;
    }

    public Nutzer startNutzer(float guthaben) {
        return new Nutzer(guthaben);
    }


}

