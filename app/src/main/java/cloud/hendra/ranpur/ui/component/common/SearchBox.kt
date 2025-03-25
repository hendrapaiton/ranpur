package cloud.hendra.ranpur.ui.component.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cloud.hendra.ranpur.domain.model.Product

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBox() {
    var searchQuery by remember { mutableStateOf("") }
    var isDropdownVisible by remember { mutableStateOf(false) }
    val allProducts = remember { getSampleSearchData() }

    val filteredResults = remember(searchQuery, allProducts) {
        if (searchQuery.isBlank()) {
            emptyList()
        } else {
            allProducts.filter {
                it.name.contains(searchQuery, ignoreCase = true)
            }
        }
    }

    Column(modifier = Modifier.padding(top = 8.dp)) {
        Box(modifier = Modifier.fillMaxWidth()) {
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { newQuery ->
                    searchQuery = newQuery
                    isDropdownVisible = newQuery.isNotBlank()
                },
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text("Temukan Barang") },
                leadingIcon = {
                    Icon(Icons.Filled.Search, contentDescription = "Pencarian...")
                },
                label = { Text("Pencarian...") }
            )

            if (isDropdownVisible && filteredResults.isNotEmpty()) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 48.dp)
                ) {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        items(filteredResults, key = { product -> product.id }) { product ->
                            Text(
                                text = product.name,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(8.dp)
                                    .clickable {
                                        searchQuery = product.name
                                        isDropdownVisible = false
                                    }
                            )
                        }
                    }
                }
            }
        }
    }
}

fun getSampleSearchData(): List<Product> {
    return listOf<Product>(
        Product(1, "Product 1", 18000.0),
        Product(2, "Product 2", 18000.0),
        Product(3, "Product 3", 18000.0),
    )
}

