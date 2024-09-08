Flowchart for Inventory Management System
1. Start
  Start: Beginning of the application.
2. Main Menu
  Display Main Menu: Show options to choose between Customer, Company Admin, and Exit.
3. Customer Path
  Handle Customer Choice

  Input Customer ID or 'new':
  If 'new':
    Create New Customer: Input name, contact number, and address.
  Else:
    Find Customer: Search for existing customer by ID.
 
Customer Found?
  Yes: Proceed to order processing.
  No: Display "Customer not found" and return to Main Menu.
  Order Processing:
  Display Order Menu:
  Add to Cart: Input product details, add to cart, and update inventory.
  Look at Cart: Display current cart items and total price.
  Clear Cart: Remove all items from cart and update inventory.
  Place Order: Add the order to the order list and return to Main Menu.
  Return: Go back to Main Menu.
4. Admin Path
  Handle Admin Choice
  Display Admin Menu:
  New Product: Add a new product to inventory.
  GST: Modify GST rate.
  Margin: Modify overhead multiplier.
  Product List: Display list of products.
  Modify/Remove Product: Find product by SKU, then modify or remove.
  Show Recent Orders: Display recent customer orders.
  Add Stock: Increase inventory count for a product.
  Return: Go back to Main Menu.
5. Exit
  Display Exit Message: Inform the user that the application is closing.
  End: Terminate the application.
  
Flowchart Description
  Start: Initiate the application.
  Main Menu: Display options to the user (Customer, Company Admin, Exit).
  If Customer:
    Input Customer ID or 'new':
      If 'new':
        Create New Customer: Capture and store details.
      If Existing Customer:
        Find Customer: Search and validate customer ID.
    Order Processing:
      Add to Cart: Input product details, adjust inventory.
      Look at Cart: Display cart content and total price.
      Clear Cart: Remove items from cart, update inventory.
      Place Order: Confirm and add order.
  Return to Main Menu.
If Company Admin:
  Display Admin Menu:
    New Product: Add product details to inventory.
    Modify GST: Update GST rate.
    Modify Margin: Update overhead multiplier.
    Show Product List: Display current products.
    Modify/Remove Product: Find product by SKU, modify or remove.
    Show Recent Orders: List recent orders.
    Add Stock: Increase stock for a product.
    Return to Main Menu.
If Exit:
  Display Exit Message.
  End: Close application.
in menu if option 4 is selected, it will show archived inventory. it is a secret menu.

# Inventory Management System

## Overview

The Inventory Management System is manages customer orders, inventory, and administrative tasks. It allows customers to place orders, view and manage their cart, and provides an administrative interface for managing products, modifying GST and overhead multipliers, and handling stock.

## Features

### Customer Features
- **Add New Customer**: Create a new customer with unique ID, name, contact number, and address.
- **Manage Cart**: Add products to the cart, view the cart, clear the cart, and place orders.
- **View Cart**: Check the items in the cart and their total price.

### Admin Features
- **Add New Product**: Add new products to the inventory with details such as name, type, brand, SKU, base price, inventory count, and weight.
- **Modify or Remove Product**: Modify existing products or remove them from the inventory. Archived products are moved to an archived list.
- **Modify GST and Overhead Multiplier**: Update GST rate and overhead multiplier used for pricing.
- **Show Recent Orders**: View recent orders placed by customers.
- **Add Stock**: Increase the inventory count for existing products.

## Files

- `Main.java`: Entry point of the application that invokes the `Menu` class.
- `Menu.java`: Contains the main menu logic and handles interactions with customers and admins.
- `Inventory.java`: Manages the product inventory, including adding, removing, and modifying products.
- `Customer.java`: Defines the customer class with attributes such as ID, name, contact number, and address.
- `Order.java`: Represents an order placed by a customer, including cart management and total price calculation.
- `CartItem.java`: Represents an item in the cart, including product details and quantity.
- `Product.java`: Represents a product with attributes such as name, type, brand, SKU, base price, inventory count, and weight.

