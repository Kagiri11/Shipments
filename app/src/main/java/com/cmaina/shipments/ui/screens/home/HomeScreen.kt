package com.cmaina.shipments.ui.screens.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun HomeScreen(){
    Surface(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "Home Screen",
        )
    }
}