package com.cmaina.shipments.ui.screens.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cmaina.shipments.ui.theme.ShipmentsPurple
import com.cmaina.shipments.ui.theme.ShipmentsSmokeWhite
import java.util.Locale

// Define colors used in this screen
private val ProfileIconSize = 100.dp
private val ProfileIconBorderWidth = 2.dp // Width of the white border around the icon if needed

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    // These would typically come from a ViewModel
    userName: String = "Kevin Fafa",
    email: String = "charlesmaish422@gmail.com",
    phoneNumber: String = "+254748779136",
    onPaymentsClick: () -> Unit = {},
    onTermsOfServiceClick: () -> Unit = {},
    onLogoutClick: () -> Unit = {}
) {

    // Profile Icon (KF)
    val initials by remember(userName) {
        derivedStateOf {
            val names = userName.split(" ").filter { it.isNotBlank() }
            if (names.isNotEmpty()) {
                val firstInitial = names.first().firstOrNull()?.toString() ?: ""
                val secondInitial = if (names.size > 1) names.last().firstOrNull()?.toString() ?: "" else ""
                (firstInitial + secondInitial).uppercase(Locale.ROOT)
            } else {
                "?" // Fallback if name is empty
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(ShipmentsSmokeWhite)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Purple Header Background
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(160.dp)
                .background(ShipmentsPurple)
        ) {}

        Surface(
            shape = CircleShape,
            color = Color.White,
            modifier = Modifier
                .size(ProfileIconSize)
                .offset(y = -(ProfileIconSize / 2) - ProfileIconBorderWidth)
                .border(ProfileIconBorderWidth, ShipmentsPurple, CircleShape)
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                Text(
                    text = initials,
                    style = MaterialTheme.typography.headlineLarge.copy(
                        fontSize = 40.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    color = ShipmentsPurple
                )
            }
        }

        Text(
            text = userName,
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(top = 8.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Divider(modifier = Modifier.padding(horizontal = 24.dp))

        Spacer(modifier = Modifier.height(8.dp))

        Column(
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
        ) {
            Text(
                text = email,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = phoneNumber,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Divider(modifier = Modifier.padding(horizontal = 24.dp))

        Spacer(modifier = Modifier.height(32.dp)) // More space before the list items

        // Options List
        Column(modifier = Modifier.fillMaxWidth()) {
            ProfileOptionRow(text = "Payments", onClick = onPaymentsClick)

            ProfileOptionRow(text = "Terms of Service", onClick = onTermsOfServiceClick)
            ProfileOptionRow(text = "Logout", onClick = onLogoutClick)
        }
        Spacer(modifier = Modifier.height(16.dp)) // Some padding at the very bottom
    }
}

@Composable
fun ProfileOptionRow(text: String, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(horizontal = 24.dp, vertical = 18.dp), // Increased vertical padding for better touch
        contentAlignment = Alignment.CenterStart
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Medium // Slightly bolder for list items
        )
    }
}

@Preview(showBackground = true, device = "spec:width=1080px,height=2340px,dpi=440")
@Composable
fun ProfileScreenPreview() {
    // Assuming you have a MaterialTheme wrapper in your actual app setup
    MaterialTheme {
        ProfileScreen()
    }
}