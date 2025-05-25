// File: com/cmaina/shipments/ui/screens/home/components/SearchActiveTopBar.kt
package com.cmaina.shipments.ui.screens.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.QrCodeScanner
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cmaina.shipments.ui.theme.ShipmentsBrown
import com.cmaina.shipments.ui.theme.ShipmentsPurple

@Composable
fun SearchActiveTopBar(
    searchQuery: String,
    onQueryChange: (String) -> Unit,
    onBackClick: () -> Unit,
    onScanClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val focusRequester = remember { FocusRequester() }
    val keyboardController = LocalSoftwareKeyboardController.current

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
        keyboardController?.show()
    }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(ShipmentsPurple)
            .padding(horizontal = 8.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = onBackClick) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Back",
                tint = Color.White
            )
        }

        BasicTextField(
            value = searchQuery,
            onValueChange = onQueryChange,
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 8.dp)
                .focusRequester(focusRequester),
            textStyle = LocalTextStyle.current.copy(color = Color.White, fontSize = MaterialTheme.typography.bodyLarge.fontSize),
            cursorBrush = SolidColor(Color.White),
            singleLine = true,
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(onSearch = { keyboardController?.hide() }),
            decorationBox = { innerTextField ->
                Box(contentAlignment = Alignment.CenterStart) {
                    if (searchQuery.isEmpty()) {
                        Text("Search by receipt number...", color = Color.White.copy(alpha = 0.7f))
                    }
                    innerTextField()
                }
            }
        )

        if (searchQuery.isNotEmpty()) {
            IconButton(onClick = { onQueryChange("") }) { // Clear text
                Icon(
                    imageVector = Icons.Filled.Clear,
                    contentDescription = "Clear Search",
                    tint = Color.White.copy(alpha = 0.7f)
                )
            }
        }

        IconButton(onClick = onScanClick) {
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .background(ShipmentsBrown, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Filled.QrCodeScanner,
                    contentDescription = "Scan Receipt",
                    tint = Color.White,
                    modifier = Modifier.size(20.dp)
                )
            }
        }
    }
}

@Preview
@Composable
fun SearchActiveTopBarPreview() {
    MaterialTheme {
        SearchActiveTopBar(searchQuery = "NEJ123", onQueryChange = {}, onBackClick = {}, onScanClick = {})
    }
}

@Preview
@Composable
fun SearchActiveTopBarEmptyPreview() {
    MaterialTheme {
        SearchActiveTopBar(searchQuery = "", onQueryChange = {}, onBackClick = {}, onScanClick = {})
    }
}