package com.example.aludelivery.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.aludelivery.model.Product
import com.example.aludelivery.sampledata.sampleProducts
import com.example.aludelivery.sampledata.sampleSections
import com.example.aludelivery.ui.Components.CardProductItem
import com.example.aludelivery.ui.Components.ProductSection
import com.example.aludelivery.ui.Components.SearcheTextField
import com.example.aludelivery.ui.theme.AluDeliveryTheme

@Composable
fun HomeScreens(
    sections: Map<String, List<Product>>,
    searchText: String = ""


) {
    Column {
        var text by remember { mutableStateOf(searchText) }

        SearcheTextField(searchText = text,
            OnsearchTextChange = {
                text = it
            },
            )


//Se o houver recomposição o código só executa de novo se o texto mudar
        val searchedProduct = remember(text) {
            if (text.isNotBlank()) {
                sampleProducts.filter { product ->
                    product.name.contains(text, ignoreCase = true) ||
                            product.description?.contains(text, ignoreCase = true) ?: false
                }
            } else emptyList()
        }

        LazyColumn(
            Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(bottom = 16.dp)

        ) {
            if (text.isBlank()) {
                for (section in sections) {
                    val title = section.key
                    val products = section.value
                    item {
                        ProductSection(
                            title = title, products = products
                        )
                    }
                }

            } else {
                items(searchedProduct) { product ->
                    CardProductItem(
                        product = product,
                        Modifier.padding(horizontal = 16.dp),
                    )
                }


            }
        }
    }
}


@Preview(showSystemUi = true)
@Composable
private fun HomeScreenPreview() {
    AluDeliveryTheme {
        Surface {
            HomeScreens(sampleSections)
        }
    }
}

@Preview
@Composable
fun HomeScreenWithSearchTextPreview() {
    AluDeliveryTheme {
        Surface {
            HomeScreens(
                sampleSections,
                searchText = "pizza",
            )
        }
    }
}