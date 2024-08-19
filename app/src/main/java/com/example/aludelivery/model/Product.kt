package com.example.aludelivery.model

import androidx.annotation.DrawableRes
import com.example.aludelivery.R
import java.math.BigDecimal

class Product (
    val name : String,
    val price : BigDecimal,
    @DrawableRes val image : Int


)
