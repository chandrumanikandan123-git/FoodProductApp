package com.yourcompany.wholesale;

import java.util.List;
import java.util.Scanner;

public class FoodProduct {
    private static FoodProductDAO foodProductDAO = new FoodProductDAO();
    private static Scanner scanner = new Scanner(System.in);

    private int id;
    private String SKU; // stock keeping unit (a unique code for each product)
    private String description;
    private String category;
    private int price;
    
    public FoodProduct() {
    	
    }

    public FoodProduct(int id, String SKU, String description, String category, int price) {
        this.id = id;
        this.SKU = SKU;
        this.description = description;
        this.category = category;
        this.price = price;
    }

    // Getter methods

    public int getId() {
        return id;
    }

    public String getSKU() {
        return SKU;
    }

    public String getDescription() {
        return description;
    }

    public String getCategory() {
        return category;
    }

    public int getPrice() {
        return price;
    }

    // Setter methods

    public void setId(int id) {
        this.id = id;
    }

    public void setSKU(String SKU) {
        this.SKU = SKU;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "FoodProduct{" +
                "id=" + id +
                ", SKU='" + SKU + '\'' +
                ", description='" + description + '\'' +
                ", category='" + category + '\'' +
                ", price=" + price +
                '}';
    }

	public static void main(String[] args) {
        String selection;
        do {
            System.out.println("--------------------");
            System.out.println("The Food Store");
            System.out.println("Choose from these options");
            System.out.println("--------------------");
            System.out.println("[1] List all products");
            System.out.println("[2] Find a product by ID");
            System.out.println("[3] Add a new product");
            System.out.println("[4] Update a product by ID");
            System.out.println("[5] Delete a product by ID");
            System.out.println("[6] Exit");
            System.out.println();

            selection = scanner.nextLine();

            switch (selection) {
                case "1":
                    listAllProducts();
                    break;
                case "2":
                    findProductById();
                    break;
                case "3":
                    addNewProduct();
                    break;
                case "4":
                    updateProductById();
                    break;
                case "5":
                    deleteProductById();
                    break;
            }
        } while (!selection.equals("6"));

        // Close the scanner and the database connection
        scanner.close();
        foodProductDAO.closeConnection();
    }

    private static void listAllProducts() {
        List<FoodProduct> products = foodProductDAO.findAllProducts();
        System.out.println("Retrieving all products:");
        for (FoodProduct product : products) {
            System.out.println("Product (id-" + product.getId() + ", SKU '" + product.getSKU() + "', description-'"
                    + product.getDescription() + "', category '" + product.getCategory() + "', price-" + product.getPrice() + ")");
        }
        System.out.println();
    }

    private static void findProductById() {
        System.out.println("Search for a product by ID");
        System.out.print("Enter product ID: ");
        int productId = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        FoodProduct product = foodProductDAO.findProduct(productId);

        if (product != null) {
            System.out.println("Product found:");
            System.out.println("Product (id-" + product.getId() + ", SKU '" + product.getSKU() + "', description-'"
                    + product.getDescription() + "', category '" + product.getCategory() + "', price-" + product.getPrice() + ")");
        } else {
            System.out.println("Product not found.");
        }

        System.out.println();
    }


    
    private static void addNewProduct() {
        System.out.println("Add a new product...");
        System.out.print("Please Enter id: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        System.out.print("Please enter SKU: ");
        String sku = scanner.nextLine();
        System.out.print("Please enter description: ");
        String description = scanner.nextLine();
        System.out.print("Please enter category: ");
        String category = scanner.nextLine();
        System.out.print("Please enter the price: ");
        int price = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        FoodProduct newProduct = new FoodProduct(id, sku, description, category, price);

        if (foodProductDAO.insertProduct(newProduct)) {
            System.out.println("Product added successfully.");
        } else {
            System.out.println("Failed to add the product.");
        }

        System.out.println();
    }


    private static void updateProductById() {
        System.out.println("Update a product by ID");
        System.out.print("Enter product ID to update: ");
        int productId = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        FoodProduct existingProduct = foodProductDAO.findProduct(productId);

        if (existingProduct != null) {
            System.out.println("Updating a product:");
            System.out.println(existingProduct);

            System.out.print("Please enter new SKU: ");
            String newSku = scanner.nextLine();
            System.out.print("Please enter new description: ");
            String newDescription = scanner.nextLine();
            System.out.print("Please enter new category: ");
            String newCategory = scanner.nextLine();
            System.out.print("Please enter the new price: ");
            int newPrice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            existingProduct.setSKU(newSku);
            existingProduct.setDescription(newDescription);
            existingProduct.setCategory(newCategory);
            existingProduct.setPrice(newPrice);

            if (foodProductDAO.updateProduct(existingProduct)) {
                System.out.println("Product updated successfully.");
            } else {
                System.out.println("Failed to update the product.");
            }
        } else {
            System.out.println("Product not found.");
        }

        System.out.println();
    }

    private static void deleteProductById() {
        System.out.println("Delete a product by ID");
        System.out.print("Enter product ID to delete: ");
        int productId = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        if (foodProductDAO.deleteProduct(productId)) {
            System.out.println("Product deleted successfully.");
        } else {
            System.out.println("Failed to delete the product.");
        }

        System.out.println();
    }
}
