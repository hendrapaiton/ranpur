package cloud.hendra.ranpur.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

data class Product(val name: String, val stock: Int, val price: Double)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductPage() {
    // Sample product data
    val allProducts = remember {
        listOf(
            Product("Product A", 10, 19.99),
            Product("Product B", 5, 29.99),
            Product("Product C", 20, 9.99),
            Product("Product D", 15, 39.99),
            Product("Product E", 8, 49.99),
        )
    }

    var searchQuery by remember { mutableStateOf("") }
    val filteredProducts = allProducts.filter {
        it.name.contains(searchQuery, ignoreCase = true)
    }

    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            // Search Bar
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                modifier = Modifier.fillMaxWidth(),
                leadingIcon = {
                    Icon(Icons.Filled.Search, contentDescription = "Search")
                },
                label = { Text("Search Products") }
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Product List
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(vertical = 8.dp)
            ) {
                items(filteredProducts) { product ->
                    ProductCard(product)
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    }
}

@Composable
fun ProductCard(product: Product) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Name: ${product.name}")
            Text(text = "Stock: ${product.stock}")
            Text(text = "Price: $${product.price}")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProductPagePreview() {
    ProductPage()
}