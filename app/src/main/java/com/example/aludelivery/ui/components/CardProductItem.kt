package com.example.aludelivery.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.aludelivery.R
import com.example.aludelivery.extesions.toBrazilianCurrency
import com.example.aludelivery.model.Product
import com.example.aludelivery.ui.theme.AluDeliveryTheme
import java.math.BigDecimal

@Composable
fun CardProductItem(
    product: Product,
    modifier: Modifier = Modifier,
    elevation: Dp = 4.dp,
    isExpanded : Boolean = false
) {
    var expanded by rememberSaveable {
        mutableStateOf(isExpanded)
    }

    Card(
        modifier
            .fillMaxWidth()
            .heightIn(150.dp)
            .clickable {
                expanded = !expanded
            },
        elevation = elevation
    ) {
        Column {
            AsyncImage(
                model = product.image,
                contentDescription = null,
                Modifier
                    .fillMaxWidth()
                    .height(100.dp),
                placeholder = painterResource(id = R.drawable.placeholder),
                contentScale = ContentScale.Crop
            )
            Column(
                Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colors.secondaryVariant)
                    .padding(16.dp)

            ) {
                Text(
                    text = product.name
                )
                Text(
                    text = product.price.toBrazilianCurrency()
                )
            }
            if (expanded) {
                product.description?.let {
                    Text(
                        text = product.description,
                        Modifier
                            .padding(16.dp)
                    )
                }
            }
        }

    }

}

@Preview(showBackground = true)
@Composable
private fun CardProductItemPreview() {
    AluDeliveryTheme {
        Surface {
            CardProductItem(
                product = Product(
                    name = "teste",
                    price = BigDecimal("9.99")
                )
            )
        }
    }
}

@Preview
@Composable
fun CardProductItemWithDescriptionPreview() {

    AluDeliveryTheme {
        Surface {
            CardProductItem(
                product = Product(
                    "teste",
                    BigDecimal("9.99"),
                    description = LoremIpsum(50).values.first()
                ),
                isExpanded = true
            )
        }
    }
}
