package com.example.aludelivery.ui.screens
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.aludelivery.R
import com.example.aludelivery.model.Product
import com.example.aludelivery.sampledata.sampleProducts
import com.example.aludelivery.ui.Components.ProductSection
import java.math.BigDecimal

@Composable
fun HomeScreens() {
    Column(
        Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Spacer(Modifier)
        ProductSection("Promoções", sampleProducts)
        ProductSection(
            "Doces", listOf(
                Product(
                    name = "chocolate",
                    price = BigDecimal("5.99"),
                    image = R.drawable.placeholder
                )
            )
        )
        ProductSection("Bebidas", sampleProducts)
        Spacer(Modifier)
    }
}

@Preview
@Composable
fun HomeSceenPreview() {
    HomeScreens()
}