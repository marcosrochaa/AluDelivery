package com.example.aludelivery.dao

import androidx.compose.runtime.mutableStateListOf
import com.example.aludelivery.model.Product

class ProductDao {

    companion object {
        private val products = mutableStateListOf<Product>()
//        (*sampleProducts.toTypedArray())
    }

    fun products() = products.toList()

    fun save (product: Product) {
        products.add(product)
    }

}
