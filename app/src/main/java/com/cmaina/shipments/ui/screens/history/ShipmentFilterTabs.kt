package com.cmaina.shipments.ui.screens.history

import androidx.compose.animation.animateColorAsState
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import com.cmaina.shipments.ui.model.TabItem
import com.cmaina.shipments.ui.model.getSampleTabItems
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cmaina.shipments.ui.theme.ShipmentsPurple


val TabIndicatorColor = Color(0xFFFFA726)
val TabBadgeBackgroundColor = TabIndicatorColor


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
            .background(ShipmentsPurple),
        edgePadding = 0.dp,
        indicator = { tabPositions ->
            if (selectedTabIndex < tabPositions.size) {
                TabRowDefaults.Indicator(
                    modifier = Modifier.tabIndicatorOffset(tabPositions[selectedTabIndex]),
                    height = 3.dp,
                    color = TabIndicatorColor
                )
            }
        }
    ) {
        tabs.forEachIndexed { index, tabItem ->
            val isSelected = selectedTabIndex == index
            val tabTextColor by animateColorAsState(if(isSelected) Color.White else Color.White.copy(alpha = 0.5F))
            val tabContainterColor by animateColorAsState(if(isSelected) TabBadgeBackgroundColor else Color.White.copy(alpha = 0.3F))
            Tab(
                modifier = Modifier.background(ShipmentsPurple),
                selected = isSelected,
                onClick = { onTabSelected(index) },
                text = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Text(
                            text = tabItem.type.displayName,
                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                        )
                        // Badge for count
                        Box(
                            modifier = Modifier
                                .height(20.dp)
                                .width(28.dp)
                                .clip(CircleShape)
                                .background(
                                    tabContainterColor
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = tabItem.count.toString(),
                                color = tabTextColor,
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                },
                selectedContentColor = Color.White,
                unselectedContentColor = Color.White.copy(alpha = 0.5F)
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