package com.example.aludelivery.ui.activities

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.aludelivery.R
import com.example.aludelivery.dao.ProductDao
import com.example.aludelivery.model.Product
import com.example.aludelivery.ui.theme.AluDeliveryTheme
import java.math.BigDecimal

class ProductFormActivity : ComponentActivity() {

    private val dao = ProductDao()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AluDeliveryTheme {
                Surface {
                    ProductFormScreen(onSaveClick = { product ->
                        dao.save(product)
                        finish()
                    })
                }
            }
        }

    }

    @Composable
    fun ProductFormScreen(
        onSaveClick: (Product) -> Unit = {}
    ) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        )
        {
            Spacer(modifier = Modifier)
            Text(
                text = "Criando o Produto ",
                Modifier.fillMaxWidth(),
                fontSize = 28.sp,
            )

            var url by remember {
                mutableStateOf("")
            }
            if (url.isNotBlank()) {
                AsyncImage(
                    model = url, contentDescription = null,
                    Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    contentScale = ContentScale.Crop,
                    placeholder = painterResource(id = R.drawable.placeholder),
                    error = painterResource(id = R.drawable.placeholder)
                )
            }


            TextField(
                value = url,
                onValueChange = {
                    url = it
                },
                Modifier.fillMaxWidth(),
                label = {
                    Text(text = "Url da imagem")
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Uri,
                    imeAction = ImeAction.Next
                )
            )

            var name by remember { mutableStateOf("") }
            TextField(
                value = name,
                onValueChange = {
                    name = it
                },
                Modifier.fillMaxWidth(),
                label = {
                    Text(text = "Nome")
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next,
                    capitalization = KeyboardCapitalization.Words

                )
            )

            var price by remember { mutableStateOf("") }
            var isPriceError by remember {
                mutableStateOf(false)
            }
            Column {
                TextField(
                    value = price,
                    onValueChange = {
                        isPriceError = try {
                            BigDecimal(it)
                            false
                        } catch (e: IllegalArgumentException) {
                            it.isNotEmpty()
                        }
                        price = it
                    },
                    Modifier.fillMaxWidth(),
                    isError = isPriceError,
                    label = {
                        Text(text = "Preço")
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Decimal,
                        imeAction = ImeAction.Next,
                    ),
                )
                if (isPriceError) {
                    Text(
                        text = "Preço deve ser um número decimal",
                        color = MaterialTheme.colors.error,
                        style = MaterialTheme.typography.caption,
                        modifier = Modifier.padding(start = 16.dp)
                    )
                }
            }

            var description by remember { mutableStateOf("") }
            TextField(
                value = description,
                onValueChange = {
                    description = it
                },
                Modifier
                    .fillMaxWidth()
                    .heightIn(min = 100.dp, max = 500.dp),
                label = {
                    Text(text = "Descrição")
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    capitalization = KeyboardCapitalization.Sentences
                )
            )

            Button(
                onClick = {
                    val convertedPrice = try {
                        BigDecimal(price)
                    } catch (e: NumberFormatException) {
                        BigDecimal.ZERO
                    }


                    val productForm = Product(
                        name = name,
                        image = url,
                        price = convertedPrice,
                        description = description
                    )
                    Log.i(
                        "ProductFormActivity",
                        "ProductFormScreen : $productForm "
                    )
                    onSaveClick(productForm)
                },
                Modifier.fillMaxWidth(),
            ) {
                Text(
                    text = "Salvar",
                    color = Color.White
                )
            }
            Spacer(modifier = Modifier)
        }

    }

    @Preview
    @Composable
    fun ProductFormScrenPreview() {
        AluDeliveryTheme {
            Surface {
                ProductFormScreen()
            }
        }

    }
}