<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Product List</title>
  <style>
    body {
      font-family: Arial, sans-serif;
      margin: 20px;
    }

    h1 {
      color: #333;
    }

    table {
      width: 100%;
      border-collapse: collapse;
      margin-top: 20px;
    }

    th, td {
      border: 1px solid #ddd;
      padding: 8px;
      text-align: left;
    }

    th {
      background-color: #f2f2f2;
    }
  </style>
</head>

<body>
  <h1>Product List</h1>
  <table id="productTable">
    <tr>
      <th>ID</th>
      <th>SKU</th>
      <th>Description</th>
      <th>Category</th>
      <th>Price</th>
    </tr>
  </table>
  
<!-- Business Customer List  -->
<h1>Business Customer List</h1>
<table id="businessCustomerTable">
  <tr>
    <th>ID</th>
    <th>Business Name</th>
    <th>Address</th>
    <th>Telephone Number</th>
  </tr>
</table>
<script>
  // Function to fetch data from the server and generate tables
  async function fetchDataAndGenerateTables(endpoint, tableId) {
    try {
      const response = await fetch(`http://localhost:3000/${endpoint}`);
      const data = await response.json();

      const table = document.getElementById(tableId);
      for (const item of data) {
        const row = table.insertRow();
        for (const key in item) {
          row.insertCell().textContent = item[key];
        }
      }
    } catch (error) {
      console.error('Error fetching data:', error);
    }
  }

  // Call functions to fetch and generate tables
  fetchDataAndGenerateTables('foodproduct', 'productTable');
  fetchDataAndGenerateTables('businesscustomer', 'businessCustomerTable');
</script>
  <script>
    

    // Function to generate the product table
    function generateProductTable() {
      const table = document.getElementById('productTable');
      for (const product of foodproduct) {
        const row = table.insertRow();
        row.insertCell(0).textContent = product.id;
        row.insertCell(1).textContent = product.SKU;
        row.insertCell(2).textContent = product.description;
        row.insertCell(3).textContent = product.category;
        row.insertCell(4).textContent = `$${product.price.toFixed(2)}`;
      }
    }

    // Call the function to generate the product table
    generateProductTable();

// Function to generate the business customer table
function generateBusinessCustomerTable() {
  const table = document.getElementById('businessCustomerTable');
  for (const customer of businessCustomers) {
    const row = table.insertRow();
    row.insertCell(0).textContent = customer.id;
    row.insertCell(1).textContent = customer.businessName;
    row.insertCell(2).textContent = customer.address;
    row.insertCell(3).textContent = customer.telephoneNumber;
  }
}

// Call the function to generate the business customer table
generateBusinessCustomerTable();

  </script>
</body>

</html>

