import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BookStorePaymentSystem {

    public static void viewCart(List<ShoppingCartItem> shoppingCart) {
        if (shoppingCart.isEmpty()) {
            System.out.println("Your shopping cart is empty.");
        } else {
            System.out.println("\nShopping Cart Contents:");
            for (int i = 0; i < shoppingCart.size(); i++) {
                ShoppingCartItem item = shoppingCart.get(i);
                Book book = item.getBook();
                int quantity = item.getQuantity();
                double itemTotal = book.getPrice() * quantity;

                System.out.println((i + 1) + ". " + book.getTitle() + " by " + book.getAuthor());
                System.out.println("   Quantity: " + quantity);
                System.out.println("   Price per item: Rs." + book.getPrice());
                System.out.println("   Total cost for this item: Rs." + itemTotal);
                System.out.println();
            }
        }
    }

    public static void checkout(List<Book> catalog, List<ShoppingCartItem> shoppingCart) {
        if (shoppingCart.isEmpty()) {
            System.out.println("Your shopping cart is empty. Cannot proceed to checkout.");
            return;
        }

        System.out.println("\nReceipt:");
        double totalAmount = 0.0;

        for (ShoppingCartItem item : shoppingCart) {
            Book book = item.getBook();
            int quantity = item.getQuantity();
            double itemTotal = book.getPrice() * quantity;

            // Update book stock
            int remainingStock = book.getStock() - quantity;
            book.setStock(remainingStock);

            System.out.println("Title: " + book.getTitle());
            System.out.println("Author: " + book.getAuthor());
            System.out.println("Quantity: " + quantity);
            System.out.println("Price per item: Rs." + book.getPrice());
            System.out.println("Total cost for this item: Rs." + itemTotal);
            System.out.println();

            totalAmount += itemTotal;
        }

        System.out.println("Total Amount: Rs." + totalAmount);
        System.out.println("Thank you for your purchase!");

        // Clear the shopping cart after checkout
        shoppingCart.clear();
    }

    public static void addToCart(List<Book> catalog, List<ShoppingCartItem> shoppingCart) {
        Scanner scanner = new Scanner(System.in);

        // Display the available books in the catalog
        System.out.println("Available Books:");
        for (int i = 0; i < catalog.size(); i++) {
            Book book = catalog.get(i);
            System.out
                    .println((i + 1) + ". " + book.getTitle() + " by " + book.getAuthor() + " - Rs." + book.getPrice());
        }

        System.out.print("Enter the number of the book you want to purchase: ");
        int bookNumber = scanner.nextInt();

        if (bookNumber >= 1 && bookNumber <= catalog.size()) {
            Book selectedBook = catalog.get(bookNumber - 1);

            System.out.print("Enter the quantity: ");
            int quantity = scanner.nextInt();

            if (quantity > 0 && quantity <= selectedBook.getStock()) {
                ShoppingCartItem cartItem = new ShoppingCartItem(selectedBook, quantity);
                shoppingCart.add(cartItem);
                System.out.println("Added to cart: " + selectedBook.getTitle() + " x" + quantity);
            } else {
                System.out.println("Invalid quantity or not enough stock.");
            }
        } else {
            System.out.println("Invalid book number.");
        }
    }

    public static void main(String[] args) {
        List<Book> catalog = new ArrayList<>();
        List<ShoppingCartItem> shoppingCart = new ArrayList<>();
        // double totalAmount = 0.0;

        catalog.add(new Book("The Great Gatsby", "F. Scott Fitzgerald", 800, 50));
        catalog.add(new Book("To Kill a Mockingbird", "Harper Lee", 500, 30));
        catalog.add(new Book("1984", "George Orwell", 320, 40));
        catalog.add(new Book("Pride and Prejudice", "Jane Austen", 290, 25));

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Book Store Payment Receipt System");
            System.out.println("1. Add Book to Cart");
            System.out.println("2. View Cart");
            System.out.println("3. Checkout");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addToCart(catalog, shoppingCart);
                    break;
                case 2:
                    // Display the current contents of the shopping cart
                    viewCart(shoppingCart);
                    break;
                case 3:
                    // Calculate the total cost, generate a payment receipt, and update book stock
                    checkout(catalog, shoppingCart);
                    break;
                case 4:
                    // Exit the program
                    System.out.println("Thank you for shopping!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
