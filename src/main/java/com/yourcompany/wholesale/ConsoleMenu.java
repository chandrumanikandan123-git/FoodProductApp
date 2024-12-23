package com.yourcompany.wholesale;

import java.util.List;

import java.util.Scanner;

public class ConsoleMenu {
    private static FoodProductDAO foodProductDAO = new FoodProductDAO();
    private static Scanner scanner = new Scanner(System.in);
    
    private static BusinessCustomerDAO businessCustomerDAO = new BusinessCustomerDAO(); // Add this line
   

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
            System.out.println("[4] Update a product");
            System.out.println("[5] Delete a product");
            System.out.println("[6] List all business customers");
            System.out.println("[7] Find a business customer by ID");
            System.out.println("[8] Add a new business customer");
            System.out.println("[9] Update a business customer");
            System.out.println("[10] Delete a business customer");
            System.out.println("[11] Exit");
            System.out.println();

            selection = scanner.nextLine();

            try {
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
                        updateProduct();
                        break;
                    case "5":
                        deleteProduct();
                        break;
                    case "6":
                        listAllBusinessCustomers();
                        break;
                    case "7":
                        findBusinessCustomerById();
                        break;
                    case "8":
                        addNewBusinessCustomer();
                        break;
                    case "9":
                        updateBusinessCustomer();
                        break;
                    case "10":
                        deleteBusinessCustomer();
                        break;
                }
            } catch (Exception e) {
                System.err.println("An error occurred: " + e.getMessage());
            }
        } while (!selection.equals("11"));

        // Close the scanner and the database connection
        scanner.close();
        foodProductDAO.closeConnection();
    }

    private static void listAllProducts() {
    	
        List<FoodProduct> products = foodProductDAO.findAllProducts();
        System.out.println("All Products:");
        for (FoodProduct product : products) {
            System.out.println(product);
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
            System.out.println(product);
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

    private static void updateProduct() {
        System.out.println("Update a product");
        System.out.print("Enter product ID: ");
        int productId = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        FoodProduct existingProduct = foodProductDAO.findProduct(productId);

        if (existingProduct != null) {
            System.out.println("Enter new details:");

            System.out.print("Enter new SKU: ");
            String newSku = scanner.nextLine();
            System.out.print("Enter new description: ");
            String newDescription = scanner.nextLine();
            System.out.print("Enter new category: ");
            String newCategory = scanner.nextLine();
            System.out.print("Enter new price: ");
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

    private static void deleteProduct() {
        System.out.println("Delete a product");
        System.out.print("Enter product ID: ");
        int productId = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        if (foodProductDAO.deleteProduct(productId)) {
            System.out.println("Product deleted successfully.");
        } else {
            System.out.println("Failed to delete the product.");
        }

        System.out.println();
    }
    private static void addNewBusinessCustomer() {
        System.out.println("Add a new business customer");
        System.out.print("Enter business name: ");
        String businessName = scanner.nextLine();

        System.out.print("Enter address line 1: ");
        String addressLine1 = scanner.nextLine();
        System.out.print("Enter address line 2: ");
        String addressLine2 = scanner.nextLine();
        System.out.print("Enter address line 3: ");
        String addressLine3 = scanner.nextLine();
        System.out.print("Enter country: ");
        String country = scanner.nextLine();
        System.out.print("Enter post code: ");
        String postCode = scanner.nextLine();

        System.out.print("Enter telephone number: ");
        String telephoneNumber = scanner.nextLine();

        Address address = new Address();
        address.setAddressLine1(addressLine1);
        address.setAddressLine2(addressLine2);
        address.setAddressLine3(addressLine3);
        address.setCountry(country);
        address.setPostCode(postCode);

        BusinessCustomer newCustomer = new BusinessCustomer(0, businessName, address, telephoneNumber);

        if (businessCustomerDAO.insertBusinessCustomer(newCustomer)) {
            System.out.println("Business Customer added successfully.");
        } else {
            System.out.println("Failed to add the Business Customer.");
        }

        System.out.println();
    }

    private static void listAllBusinessCustomers() {
        List<BusinessCustomer> customers = businessCustomerDAO.findAllBusinessCustomers();
        System.out.println("All Business Customers:");
        for (BusinessCustomer customer : customers) {
            System.out.println(customer);
        }
        System.out.println();
    }

    private static void findBusinessCustomerById() {
        System.out.println("Search for a business customer by ID");
        System.out.print("Enter business customer ID: ");
        int customerId = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        BusinessCustomer customer = businessCustomerDAO.findBusinessCustomer(customerId);

        if (customer != null) {
            System.out.println("Business Customer found:");
            System.out.println(customer);
        } else {
            System.out.println("Business Customer not found.");
        }

        System.out.println();
    }

//    private static void addNewBusinessCustomer() {
//        System.out.println("Add a new business customer");
//        System.out.print("Enter business name: ");
//        String businessName = scanner.nextLine();
//
//        System.out.print("Enter address line 1: ");
//        String addressLine1 = scanner.nextLine();
//        System.out.print("Enter address line 2: ");
//        String addressLine2 = scanner.nextLine();
//        System.out.print("Enter address line 3: ");
//        String addressLine3 = scanner.nextLine();
//        System.out.print("Enter country: ");
//        String country = scanner.nextLine();
//        System.out.print("Enter post code: ");
//        String postCode = scanner.nextLine();
//
//        System.out.print("Enter telephone number: ");
//        String telephoneNumber = scanner.nextLine();
//
//        Address address = new Address();
//        address.setAddressLine1(addressLine1);
//        address.setAddressLine2(addressLine2);
//        address.setAddressLine3(addressLine3);
//        address.setCountry(country);
//        address.setPostCode(postCode);
//
//        BusinessCustomer newCustomer = new BusinessCustomer(0, businessName, address, telephoneNumber);
//
//        if (businessCustomerDAO.insertBusinessCustomer(newCustomer)) {
//            System.out.println("Business Customer added successfully.");
//        } else {
//            System.out.println("Failed to add the Business Customer.");
//        }
//
//        System.out.println();
//    }

    private static void updateBusinessCustomer() {
        System.out.println("Update a business customer");
        System.out.print("Enter business customer ID: ");
        int customerId = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        BusinessCustomer existingCustomer = businessCustomerDAO.findBusinessCustomer(customerId);

        if (existingCustomer != null) {
            System.out.println("Enter new details:");

            System.out.print("Enter new business name: ");
            String newBusinessName = scanner.nextLine();

            System.out.print("Enter new address line 1: ");
            String newAddressLine1 = scanner.nextLine();
            System.out.print("Enter new address line 2: ");
            String newAddressLine2 = scanner.nextLine();
            System.out.print("Enter new address line 3: ");
            String newAddressLine3 = scanner.nextLine();
            System.out.print("Enter new country: ");
            String newCountry = scanner.nextLine();
            System.out.print("Enter new post code: ");
            String newPostCode = scanner.nextLine();

            System.out.print("Enter new telephone number: ");
            String newTelephoneNumber = scanner.nextLine();

            Address newAddress = new Address();
            newAddress.setAddressLine1(newAddressLine1);
            newAddress.setAddressLine2(newAddressLine2);
            newAddress.setAddressLine3(newAddressLine3);
            newAddress.setCountry(newCountry);
            newAddress.setPostCode(newPostCode);

            existingCustomer.setBusinessName(newBusinessName);
            existingCustomer.setAddress(newAddress);
            existingCustomer.setTelephoneNumber(newTelephoneNumber);

            if (businessCustomerDAO.updateBusinessCustomer(existingCustomer)) {
                System.out.println("Business Customer updated successfully.");
            } else {
                System.out.println("Failed to update the Business Customer.");
            }
        } else {
            System.out.println("Business Customer not found.");
        }

        System.out.println();
    }

    private static void deleteBusinessCustomer() {
        System.out.println("Delete a business customer");
        System.out.print("Enter business customer ID: ");
        int customerId = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        if (businessCustomerDAO.deleteBusinessCustomer(customerId)) {
            System.out.println("Business Customer deleted successfully.");
        } else {
            System.out.println("Failed to delete the Business Customer.");
        }

        System.out.println();
    }
    
    
    
}


