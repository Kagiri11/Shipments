package com.cmaina.shipments.ui.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.BadgedBox // For potential notification count
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api // For BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.cmaina.shipments.R // Assuming you have a placeholder drawable
import com.cmaina.shipments.domain.model.user.UserDisplayInfo
import com.cmaina.shipments.ui.theme.ShipmentsPurple


// Color defined in HomeScreen.kt or your Theme.kt
// val HomeScreenHeaderColor = Color(0xFF4A004E)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeHeaderSection(
    userDisplayInfo: UserDisplayInfo,
    notificationCount: Int,
    onLocationClick: () -> Unit,
    onNotificationClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(ShipmentsPurple)
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        // Left side: Profile Image and Location
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = rememberAsyncImagePainter(
                    ImageRequest.Builder(LocalContext.current)
                        .data(userDisplayInfo.profileImageUrl)
                        .apply(block = fun ImageRequest.Builder.() {
                            crossfade(true)
                            placeholder(R.drawable.ic_launcher_background) // Add a placeholder drawable
                            error(R.drawable.ic_launcher_background)       // Add an error drawable
                        }).build()
                ),
                contentDescription = "User Profile Image",
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column {
                Text(
                    text = "Your location",
                    color = Color.White.copy(alpha = 0.8f),
                    fontSize = 12.sp
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.clickable(onClick = onLocationClick)
                ) {
                    Text(
                        text = userDisplayInfo.currentLocation,
                        color = Color.White,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium
                    )
                    Icon(
                        imageVector = Icons.Filled.ArrowDropDown,
                        contentDescription = "Change location",
                        tint = Color.White,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
        }

        // Right side: Notification Icon
        // If you want to show a badge for notificationCount > 0
        Card(
            modifier = Modifier.size(50.dp),
            shape = CircleShape,
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
            onClick = onNotificationClick
        ) {
            IconButton(onClick = {}, modifier = Modifier.fillMaxSize()) {
                Icon(
                    tint = Color.Black,
                    imageVector = Icons.Outlined.Notifications,
                    contentDescription = "Notifications"
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeHeaderSectionPreview() {
    MaterialTheme {
        HomeHeaderSection(
            userDisplayInfo = UserDisplayInfo(
                profileImageUrl = null, // Test with null to see placeholder
                currentLocation = "Wertheimer, Illinois"
            ),
            notificationCount = 3, // Test with notifications
            onLocationClick = {},
            onNotificationClick = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HomeHeaderSectionNoNotificationPreview() {
    MaterialTheme {
        HomeHeaderSection(
            userDisplayInfo = UserDisplayInfo(
                profileImageUrl = "https://example.com/image.jpg", // Test with a dummy URL
                currentLocation = "Nairobi, Kenya"
            ),
            notificationCount = 0, // Test with no notifications
            onLocationClick = {},
            onNotificationClick = {}
        )
    }
}