package com.example.aludelivery.ui.Components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SearcheTextField(searchText: String,
                     OnsearchTextChange: (String) -> Unit) {

    OutlinedTextField(
        value = searchText,
        onValueChange = { newValue ->
            OnsearchTextChange(newValue)
        },
        Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(100),
        leadingIcon = {
            Icon(Icons.Default.Search, contentDescription = "Search Icon")
        },
        label = {
            Text(text = "Produto")
        },
        placeholder = {
            Text(text = "O que você Procura")
        }

    )

}
