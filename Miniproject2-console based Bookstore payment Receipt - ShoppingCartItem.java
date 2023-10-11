class ShoppingCartItem {
    private Book book;
    private int quantity;

    public ShoppingCartItem(Book book, int quantity) {
        this.book = book;
        this.quantity = quantity;
    }

    // Getters and setters
    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuatity(int quantity) {
        this.quantity = quantity;
    }
}
