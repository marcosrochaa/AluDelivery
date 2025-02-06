package com.example.aludelivery.ui.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.aludelivery.dao.ProductDao
import com.example.aludelivery.sampledata.sampleSections
import com.example.aludelivery.ui.screens.HomeScreen
import com.example.aludelivery.ui.screens.HomeScreenContent
import com.example.aludelivery.ui.screens.HomeScreenUiState
import com.example.aludelivery.ui.theme.AluDeliveryTheme

class MainActivity : ComponentActivity() {

    private val dao = ProductDao()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            APP(onFabClick = {
                startActivity(
                    Intent(
                        this,
                        ProductFormActivity::class.java
                    )
                )
            }) {
                val products = dao.products()
                HomeScreen(products = products)
            }
        }
    }
}

@Composable
fun APP(
    onFabClick: () -> Unit = {},
    content: @Composable () -> Unit = {},
) {
    AluDeliveryTheme {
        Surface {
            Scaffold(floatingActionButton = {
                FloatingActionButton(onClick = onFabClick) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = null)
                }
            }) { paddingValues ->
                Box(modifier = Modifier.padding(paddingValues)) {
                    content()
                }
            }
        }
    }
}

@Preview
@Composable
fun APPPreview() {
    APP {
        HomeScreenContent(HomeScreenUiState(sampleSections))
    }
}




