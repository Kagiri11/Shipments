package com.cmaina.shipments.ui.screens.history

import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import com.cmaina.shipments.ui.model.TabItem
import com.cmaina.shipments.ui.model.getSampleTabItems
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// Define colors (or import from your theme's color file)
val TabIndicatorColor = Color(0xFFFFA726) // Orange
val TabBadgeBackgroundColor = TabIndicatorColor
val TabBadgeContentColor = Color.Black // Using black for better contrast on orange
val TabRowContainerColor = PurpleAppBarBackground // Defined in TopAppBar section
val SelectedTabContentColor = Color.White
val UnselectedTabContentColor = Color.White.copy(alpha = 0.7f)


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShipmentFilterTabs(
    tabs: List<TabItem>,
    selectedTabIndex: Int,
    onTabSelected: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    ScrollableTabRow(
        selectedTabIndex = selectedTabIndex,
        modifier = modifier
            .fillMaxWidth()
            .background(TabRowContainerColor), // Purple background for the TabRow container
        edgePadding = 0.dp, // No extra padding at the edges of the TabRow
        indicator = { tabPositions ->
            if (selectedTabIndex < tabPositions.size) {
                TabRowDefaults.Indicator(
                    modifier = Modifier.tabIndicatorOffset(tabPositions[selectedTabIndex]),
                    height = 3.dp,
                    color = TabIndicatorColor // Orange indicator
                )
            }
        },
        divider = { /* No divider, or customize if needed */ }
    ) {
        tabs.forEachIndexed { index, tabItem ->
            Tab(
                selected = selectedTabIndex == index,
                onClick = { onTabSelected(index) },
                text = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Text(
                            text = tabItem.type.displayName,
                            fontWeight = if (selectedTabIndex == index) FontWeight.Bold else FontWeight.Normal
                        )
                        // Badge for count
                        Box(
                            modifier = Modifier
                                .size(20.dp) // Adjust size as needed
                                .clip(CircleShape)
                                .background(TabBadgeBackgroundColor),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = tabItem.count.toString(),
                                color = TabBadgeContentColor,
                                fontSize = 10.sp, // Adjust font size as needed
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                },
                selectedContentColor = SelectedTabContentColor, // White for selected tab text
                unselectedContentColor = UnselectedTabContentColor // Lighter white for unselected
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ShipmentFilterTabsPreview() {
    MaterialTheme {
        ShipmentFilterTabs(
            tabs = getSampleTabItems(),
            selectedTabIndex = 0,
            onTabSelected = {}
        )
    }
}