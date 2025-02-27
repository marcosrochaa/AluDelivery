package com.example.aludelivery.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.aludelivery.model.Product
import com.example.aludelivery.sampledata.sampleCandies
import com.example.aludelivery.sampledata.sampleDrinks
import com.example.aludelivery.sampledata.sampleProducts
import com.example.aludelivery.sampledata.sampleSections
import com.example.aludelivery.ui.components.CardProductItem
import com.example.aludelivery.ui.components.ProductSection
import com.example.aludelivery.ui.components.SearchTextField
import com.example.aludelivery.ui.theme.AluDeliveryTheme

class HomeScreenUiState(searchText: String = "") {

    var text by mutableStateOf(searchText)
        private set

    val searchedProducts get() =
        if (text.isNotBlank()) {
            sampleProducts.filter { product ->
                product.name.contains(
                    text,
                    ignoreCase = true,
                ) ||
                        product.description?.contains(
                            text,
                            ignoreCase = true,
                        ) ?: false
            }
        } else emptyList()

    fun isShowSections(): Boolean {
        return text.isBlank()
    }

    val onSearchChange: (String) -> Unit = { searchText ->
        text = searchText
    }

}

@Composable
fun HomeScreenContent(
    sections: Map<String, List<Product>>,
    state: HomeScreenUiState = HomeScreenUiState()
) {
    Column {
        val text = state.text
        val searchedProducts = remember(text) {
            state.searchedProducts
        }
        SearchTextField(
            searchText = text,
            onSearchChange = state.onSearchChange,
            Modifier
                .padding(16.dp)
                .fillMaxWidth(),
        )

        LazyColumn(
            Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(bottom = 16.dp)
        ) {
            if (state.isShowSections()) {
                for (section in sections) {
                    val title = section.key
                    val products = section.value
                    item {
                        ProductSection(
                            title = title,
                            products = products
                        )
                    }
                }
            } else {
                items(searchedProducts) { p ->
                    CardProductItem(
                        product = p,
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
    AluDeliveryTheme{
        Surface {
            HomeScreenContent(sampleSections)
        }
    }
}

@Preview
@Composable
fun HomeScreenWithSearchTextPreview() {
    AluDeliveryTheme{
        Surface {
            HomeScreenContent(
                sampleSections,
                state = HomeScreenUiState(searchText = "a"),
            )
        }
    }
}