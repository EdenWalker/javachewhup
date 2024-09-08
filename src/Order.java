import java.util.ArrayList;

public class Order {
    private String customerId;
    private ArrayList<CartItem> cartItems;

    public Order(String customerId) {
        this.customerId = customerId;
        this.cartItems = new ArrayList<>();
    }

    public String getCustomerId() {
        return customerId;
    }

    public ArrayList<CartItem> getCartItems() {
        return cartItems;
    }

    public void addProduct(Product product, int quantity) {
        for (CartItem item : cartItems) {
            if (item.getProduct().equals(product)) {
                item.setQuantity(item.getQuantity() + quantity);
                return;
            }
        }
        cartItems.add(new CartItem(product, quantity));
    }

    public void clearCart() {
        cartItems.clear();
    }

    public double calculateTotalPrice(double overheadMult, double gst) {
        double total = 0;
        for (CartItem item : cartItems) {
            Product product = item.getProduct();
            int quantity = item.getQuantity();
            double basePrice = product.getBasePrice();
            double price = basePrice * overheadMult;
            double gstAmount = price * gst;
            double finalPrice = price + gstAmount;
            total += finalPrice * quantity;
        }
        return total;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (CartItem item : cartItems) {
            sb.append(item).append("\n");
        }
        return sb.toString();
    }
}
