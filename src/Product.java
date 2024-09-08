public class Product {
    private String name;
    private String type;
    private String brand;
    private String sku;
    private double basePrice;
    private int inventoryCount;
    private double weight;  // Add weight attribute

    public Product(String name, String type, String brand, String sku, double basePrice, int inventoryCount, double weight) {
        this.name = name;
        this.type = type;
        this.brand = brand;
        this.sku = sku;
        this.basePrice = basePrice;
        this.inventoryCount = inventoryCount;
        this.weight = weight;
    }

    public String getName() { return name; }
    public String getType() { return type; }
    public String getBrand() { return brand; }
    public String getSku() { return sku; }
    public double getBasePrice() { return basePrice; }
    public int getInventoryCount() { return inventoryCount; }
    public double getWeight() { return weight; }  // Getter for weight

    public void setInventoryCount(int inventoryCount) { this.inventoryCount = inventoryCount; }

    @Override
    public String toString() {
        return "Product[name=" + name + ", type=" + type + ", brand=" + brand + ", sku=" + sku
                + ", basePrice=" + basePrice + ", inventoryCount=" + inventoryCount + ", weight=" + weight + "]";
    }
}
