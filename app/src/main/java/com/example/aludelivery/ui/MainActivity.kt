package com.example.aludelivery.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import com.example.aludelivery.sampledata.sampleCandies
import com.example.aludelivery.sampledata.sampleSections
import com.example.aludelivery.ui.screens.HomeScreens
import com.example.aludelivery.ui.theme.AluDeliveryTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            APP()
        }
    }
}

@Composable
fun APP() {
    AluDeliveryTheme {
        Surface {
         HomeScreens(sampleSections)
        }
    }
}




