package com.example.aludelivery.sampledata

import com.example.aludelivery.R
import com.example.aludelivery.model.Product
import java.math.BigDecimal

val sampleProducts = listOf(
    Product(
        name = "Hamburguer",
        price = BigDecimal("14.99"),
        image = R.drawable.hamburguer
    ),
    Product(
        name = "Pizza",
        price = BigDecimal("30.99"),
        image = R.drawable.pizza
    ),
    Product(
        name = "Batata Frita",
        price = BigDecimal("15.99"),
        image = R.drawable.batatafrita
    )
)
