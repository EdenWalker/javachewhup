import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    private static Scanner sc = new Scanner(System.in);
    private static Inventory inventory = new Inventory();
    private static ArrayList<Customer> customers = new ArrayList<>();
    private static ArrayList<Order> orders = new ArrayList<>();
    private static int customerIDCounter = 1;

    public static void main(String[] args) {
        while (true) {
            System.out.println("1. Customer");
            System.out.println("2. Company Admin");
            System.out.println("3. Exit");


            int choice = sc.nextInt();
            sc.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    handleCustomer();
                    break;
                case 2:
                    handleAdmin();
                    break;
                case 3:
                    System.out.println("Exiting...");
                    return;
                case 4:
                    viewArchivedProducts();
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // New method to view archived products
    private static void viewArchivedProducts() {
        System.out.println("Archived Products:");
        for (Product product : inventory.getArchivedProducts()) {
            System.out.println(product);
        }
    }

    private static void handleCustomer() {
        System.out.print("Enter Customer ID or type 'new' for a new customer: ");
        String input = sc.nextLine();

        Customer customer = null;
        if (input.equalsIgnoreCase("new")) {
            System.out.print("Enter Name: ");
            String name = sc.nextLine();
            System.out.print("Enter Contact Number: ");
            String contactNumber = sc.nextLine();
            System.out.print("Enter Address: ");
            String address = sc.nextLine();

            String customerID = "C" + (customerIDCounter++);
            customer = new Customer(customerID, name, contactNumber, address);
            customers.add(customer);
            System.out.println("Customer created with ID: " + customerID);
        } else {
            for (Customer c : customers) {
                if (c.getId().equals(input)) {
                    customer = c;
                    break;
                }
            }
            if (customer == null) {
                System.out.println("Customer not found.");
                return;
            }
        }

        Order order = new Order(customer.getId());
        while (true) {
            System.out.println("1. Add to Cart");
            System.out.println("2. Look at Cart");
            System.out.println("3. Clear Cart");
            System.out.println("4. Place Order");
            System.out.println("5. Return");

            int choice = sc.nextInt();
            sc.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    addToCart(order);
                    break;
                case 2:
                    lookAtCart(order);
                    break;
                case 3:
                    clearCart(order);
                    break;
                case 4:
                    placeOrder(order);
                    return;  // Return to main menu after placing order
                case 5:
                    return;  // Return to main menu
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void addToCart(Order order) {
        System.out.print("Enter Type: ");
        String type = sc.nextLine();
        System.out.print("Enter Brand: ");
        String brand = sc.nextLine();
        System.out.print("Enter Weight: ");
        double weight = sc.nextDouble();
        System.out.print("Enter Amount: ");
        int amt = sc.nextInt();
        sc.nextLine();  // Consume newline

        for (Product product : inventory.getProducts()) {
            if (product.getType().equalsIgnoreCase(type) && product.getBrand().equalsIgnoreCase(brand) && product.getWeight() == weight) {
                if (product.getInventoryCount() >= amt) {
                    double basePrice = product.getBasePrice();
                    double price = basePrice * inventory.getOverheadMult();
                    double gstAmount = price * inventory.getGst();
                    double finalPrice = price + gstAmount;
                    System.out.println("Item: " + product.getName() + ", Price: " + finalPrice);
                    System.out.print("Add to cart (Y/N)? ");
                    String addToCart = sc.nextLine();
                    if (addToCart.equalsIgnoreCase("Y")) {
                        order.addProduct(product, amt);
                        product.setInventoryCount(product.getInventoryCount() - amt);
                        System.out.println("Product added to cart.");
                        // Update the cart and display it immediately
                        lookAtCart(order);
                    }
                } else {
                    System.out.println("Insufficient inventory.");
                }
                return;
            }
        }
        System.out.println("Product not found.");
    }

    private static void lookAtCart(Order order) {
        System.out.println("Cart: ");
        StringBuilder sb = new StringBuilder();
        double totalPrice = 0;

        for (CartItem item : order.getCartItems()) {
            Product product = item.getProduct();
            int quantity = item.getQuantity();
            double basePrice = product.getBasePrice();
            double overheadMult = inventory.getOverheadMult();
            double gst = inventory.getGst();

            double price = basePrice * overheadMult;
            double gstAmount = price * gst;
            double finalPrice = price + gstAmount;

            sb.append(product.getName())
                    .append(", Price: ")
                    .append(String.format("%.2f", finalPrice))
                    .append(", Quantity: ")
                    .append(quantity)
                    .append("\n");

            totalPrice += finalPrice * quantity;
        }

        System.out.println(sb.toString());
        System.out.println(String.format("Total Price: %.2f", totalPrice));
    }

    private static void clearCart(Order order) {
        for (CartItem item : order.getCartItems()) {
            Product product = item.getProduct();
            int quantity = item.getQuantity();
            product.setInventoryCount(product.getInventoryCount() + quantity);
        }
        order.clearCart();
        System.out.println("Cart cleared.");
    }

    private static void placeOrder(Order order) {
        orders.add(order);
        System.out.println("Order placed");
    }

    private static void handleAdmin() {
        while (true) {
            System.out.println("1. New Product");
            System.out.println("2. GST");
            System.out.println("3. Margin");
            System.out.println("4. Product List");
            System.out.println("5. Modify/Remove Product");
            System.out.println("6. Show Recent Orders");
            System.out.println("7. Add Stock");
            System.out.println("8. Return");

            int choice = sc.nextInt();
            sc.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    addNewProduct();
                    break;
                case 2:
                    modifyGst();
                    break;
                case 3:
                    modifyMargin();
                    break;
                case 4:
                    showProductList();
                    break;
                case 5:
                    modifyOrRemoveProduct();
                    break;
                case 6:
                    showRecentOrders();
                    break;
                case 7:
                    addStock();
                    break;
                case 8:
                    return;  // Return to main menu
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void addNewProduct() {
        System.out.print("Enter Item Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Type: ");
        String type = sc.nextLine();
        System.out.print("Enter Brand: ");
        String brand = sc.nextLine();
        System.out.print("Enter Weight: ");
        double weight = sc.nextDouble();
        sc.nextLine();// Consume newline
        System.out.print("Enter SKU: ");
        String sku = sc.nextLine();

        System.out.print("Enter Base Price: ");
        double basePrice = sc.nextDouble();
        sc.nextLine();
        System.out.print("Enter Inventory Count: ");
        int inventoryCount = sc.nextInt();
        sc.nextLine();  // Consume newline

        Product product = new Product(name, type, brand, sku, basePrice, inventoryCount, weight);
        inventory.addProduct(product);
        System.out.println("Product added: " + product);
    }

    private static void modifyGst() {
        System.out.println("Current GST: " + inventory.getGst());
        System.out.print("Enter new GST: ");
        double newGst = sc.nextDouble();
        sc.nextLine();  // Consume newline
        inventory.setGst(newGst);
        System.out.println("GST updated to: " + inventory.getGst());
    }

    private static void modifyMargin() {
        System.out.println("Current Overhead Multiplier: " + inventory.getOverheadMult());
        System.out.print("Enter new Overhead Multiplier: ");
        double newOverheadMult = sc.nextDouble();
        sc.nextLine();  // Consume newline
        inventory.setOverheadMult(newOverheadMult);
        System.out.println("Overhead Multiplier updated to: " + inventory.getOverheadMult());
    }

    private static void showProductList() {
        System.out.println("Product List: ");
        for (Product product : inventory.getProducts()) {
            System.out.println(product);
        }
    }

    private static void modifyOrRemoveProduct() {
        System.out.print("Enter SKU of the product to modify/remove: ");
        String sku = sc.nextLine();

        for (Product product : inventory.getProducts()) {
            if (product.getSku().equals(sku)) {
                System.out.println("Product found: " + product);
                System.out.println("1. Delete");
                System.out.println("2. Modify");
                System.out.println("3. Return");

                int choice = sc.nextInt();
                sc.nextLine();  // Consume newline

                switch (choice) {
                    case 1:
                        inventory.removeProduct(sku);
                        System.out.println("Product removed.");
                        return;
                    case 2:
                        modifyProduct(sku);
                        return;
                    case 3:
                        return;  // Return to admin menu
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
                return;
            }
        }
        System.out.println("Product not found.");
    }

    private static void modifyProduct(String sku) {
        System.out.print("Enter new Item Name (or type 'r or remain' to keep current value): ");
        String name = sc.nextLine();
        System.out.print("Enter new Type (or type 'r or remain' to keep current value): ");
        String type = sc.nextLine();
        System.out.print("Enter new Brand (or type 'r or remain' to keep current value): ");
        String brand = sc.nextLine();
        System.out.print("Enter new Weight (or type 'r or remain' to keep current value): ");
        String weightStr = sc.nextLine();
        System.out.print("Enter new Base Price (or type 'r or remain' to keep current value): ");
        String basePriceStr = sc.nextLine();
        System.out.print("Enter new Inventory Count (or type 'r or remain' to keep current value): ");
        String inventoryCountStr = sc.nextLine();

        double weight = -1;
        double basePrice = -1;
        int inventoryCount = -1;

        if (!weightStr.equalsIgnoreCase("remain")) {
            weight = Double.parseDouble(weightStr);
        }

        if (!basePriceStr.equalsIgnoreCase("remain")) {
            basePrice = Double.parseDouble(basePriceStr);
        }

        if (!inventoryCountStr.equalsIgnoreCase("remain")) {
            inventoryCount = Integer.parseInt(inventoryCountStr);
        }

        Product updatedProduct = null;
        for (Product product : inventory.getProducts()) {
            if (product.getSku().equals(sku)) {
                updatedProduct = new Product(
                        name.equalsIgnoreCase("remain") ? product.getName() : name,
                        type.equalsIgnoreCase("remain") ? product.getType() : type,
                        brand.equalsIgnoreCase("remain") ? product.getBrand() : brand,
                        sku,
                        basePrice < 0 ? product.getBasePrice() : basePrice,
                        inventoryCount < 0 ? product.getInventoryCount() : inventoryCount,
                        weight < 0 ? product.getWeight() : weight
                );
                inventory.modifyProduct(sku, updatedProduct);
                System.out.println("Product modified: " + updatedProduct);
                return;
            }
        }
        System.out.println("Product not found.");
    }

    private static void showRecentOrders() {
        System.out.println("Recent Orders: ");
        for (Order order : orders) {
            System.out.println("Customer ID: " + order.getCustomerId());
            System.out.println(order);
        }
    }

    private static void addStock() {
        System.out.print("Enter SKU of the product to add stock: ");
        String sku = sc.nextLine();
        System.out.print("Enter quantity to add: ");
        int quantity = sc.nextInt();
        sc.nextLine();  // Consume newline

        inventory.addStock(sku, quantity);
        System.out.println("Stock added.");
    }
}
