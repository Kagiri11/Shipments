package com.cmaina.shipments.ui.screens.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cmaina.shipments.R
import com.cmaina.shipments.domain.model.search.ShipmentSearchResult

@Composable
fun SearchResultsList(
    results: List<ShipmentSearchResult>,
    isLoading: Boolean,
    searchQuery: String,
    onItemClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White) // White background for the list area
    ) {
        if (isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        } else if (results.isEmpty() && searchQuery.isNotBlank()) {
            Text(
                text = stringResource(R.string.no_results_found_for, searchQuery),
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(16.dp),
                style = MaterialTheme.typography.bodyMedium
            )
        } else {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(results, key = { it.id }) { item ->
                    ShipmentSearchResultItemView(
                        item = item,
                        onClick = { onItemClick(item.id) }
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SearchResultsListPreview() {
    MaterialTheme {
        SearchResultsList(
            results = listOf(
                ShipmentSearchResult("1", "Macbook pro M2", "#NE43857340857904", "Paris", "Morocco"),
                ShipmentSearchResult("2", "Summer linen jacket", "#NEJ20089934122231", "Barcelona", "Paris")
            ),
            isLoading = false,
            searchQuery = "Macbook",
            onItemClick = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SearchResultsListLoadingPreview() {
    MaterialTheme {
        SearchResultsList(
            results = emptyList(),
            isLoading = true,
            searchQuery = "Searching...",
            onItemClick = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SearchResultsListNoResultsPreview() {
    MaterialTheme {
        SearchResultsList(
            results = emptyList(),
            isLoading = false,
            searchQuery = "NonExistentItem123",
            onItemClick = {}
        )
    }
}