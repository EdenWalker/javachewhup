import java.util.ArrayList;

public class Inventory {
    private ArrayList<Product> products;
    private ArrayList<Product> archivedProducts;
    private double overheadMult = 1.3;
    private double gst = 0.09;

    public Inventory() {
        products = new ArrayList<>();
        archivedProducts = new ArrayList<>();
        // Initialize with example products including weight
        products.add(new Product("rice flour", "rice", "erawan", "RFER25", 18, 100, 25));
        products.add(new Product("corn starch", "corn starch", "A1", "CSA125", 20, 100, 25));
        products.add(new Product("glutinous rice flour", "grice", "erawan", "GRFER25", 20, 100, 25));
        products.add(new Product("wheat maltose", "malt", "3 goat", "wm3g25", 17, 100, 25));
    }

    public ArrayList<Product> getProducts() { return products; }
    public ArrayList<Product> getArchivedProducts() { return archivedProducts; }
    public double getOverheadMult() { return overheadMult; }
    public double getGst() { return gst; }

    public void addProduct(Product product) {
        products.add(product);
    }

    public void removeProduct(String sku) {
        for (Product product : products) {
            if (product.getSku().equals(sku)) {
                archivedProducts.add(product);
                products.remove(product);
                return;
            }
        }
    }

    public void modifyProduct(String sku, Product newProduct) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getSku().equals(sku)) {
                products.set(i, newProduct);
                return;
            }
        }
    }

    public void addStock(String sku, int quantity) {
        for (Product product : products) {
            if (product.getSku().equals(sku)) {
                product.setInventoryCount(product.getInventoryCount() + quantity);
                return;
            }
        }
    }

    public void setGst(double newGst) {
        this.gst = newGst;
    }

    public void setOverheadMult(double newOverheadMult) {
        this.overheadMult = newOverheadMult;
    }

    @Override
    public String toString() {
        return "Inventory[products=" + products + ", archivedProducts=" + archivedProducts + "]";
    }
}
