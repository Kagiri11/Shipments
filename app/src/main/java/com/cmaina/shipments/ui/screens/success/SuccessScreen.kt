package com.cmaina.shipments.ui.screens.success

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun SuccessScreen(){
    Surface(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "Success Screen",
        )
    }
}