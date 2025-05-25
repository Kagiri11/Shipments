// File: com/cmaina/shipments/ui/screens/success/SuccessScreen.kt
package com.cmaina.shipments.ui.screens.success

import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocalShipping // Placeholder for logo truck
import androidx.compose.material.icons.filled.Inventory2 // Placeholder for package image
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LifecycleEventEffect
import androidx.lifecycle.compose.LifecycleStartEffect
import com.cmaina.shipments.R // Assuming your R file for drawables
import com.cmaina.shipments.ui.theme.ShipmentsBrown
import com.cmaina.shipments.ui.theme.ShipmentsPurple
import com.cmaina.shipments.ui.theme.ShipmentsSmokeWhite

// Define colors used in this screen
private val SuccessAmountColor = Color(0xFF2E7D32) // A shade of green
private val SuccessCurrencyColor = Color(0xFF4CAF50) // A lighter or complementary green
private val SuccessDisclaimerColor = Color.Gray
private val SuccessButtonBackgroundColor = Color(0xFFFFA726) // Orange, similar to Calculate button
private val SuccessButtonTextColor = Color.White
private val LogoTextColor = Color(0xFF3F51B5) // Example: Indigo for "MoveMate"

@Composable
fun SuccessScreen(
    estimatedAmount: String = "1456",
    currency: String = "USD",
    isDarkTheme: Boolean = isSystemInDarkTheme(),
    onBackToHomeClick: () -> Unit = {}
) {
    val view = LocalView.current
    val window = (view.context as Activity).window

    LifecycleStartEffect(Unit) {
        window.statusBarColor = ShipmentsSmokeWhite.toArgb()
        WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = isDarkTheme
        onStopOrDispose {
                window.statusBarColor = ShipmentsPurple.toArgb()
                WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = isDarkTheme
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(ShipmentsSmokeWhite)
            .padding(horizontal = 24.dp, vertical = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween // Pushes button to bottom if content is sparse
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            // Logo Section
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "MoveMate",
                    fontSize = 28.sp, // Adjust as needed
                    fontWeight = FontWeight.Bold,
                    color = LogoTextColor // Use your brand color
                    // fontFamily = YourCustomFont // If you have a custom font for logo
                )
                Spacer(modifier = Modifier.width(8.dp))
                Icon(
                    imageVector = Icons.Filled.LocalShipping, // Replace with your actual logo icon
                    contentDescription = "Logo Truck",
                    tint = SuccessButtonBackgroundColor, // Using orange for the truck icon
                    modifier = Modifier.size(32.dp)
                )
            }

            Spacer(modifier = Modifier.height(48.dp))

            // Package Image
            Image(
                // Replace with your actual drawable resource for the package
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = "Package Illustration",
                modifier = Modifier.size(180.dp) // Adjust size as needed
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Title
            Text(
                text = "Total Estimated Amount",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Amount
            Text(
                buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            color = SuccessAmountColor,
                            fontSize = 48.sp, // Large font for amount
                            fontWeight = FontWeight.Bold
                        )
                    ) {
                        append("$$estimatedAmount") // Assuming currency symbol is needed
                    }
                    append(" ")
                    withStyle(
                        style = SpanStyle(
                            color = SuccessCurrencyColor,
                            fontSize = 20.sp, // Smaller font for currency code
                            fontWeight = FontWeight.Normal
                        )
                    ) {
                        append(currency)
                    }
                }
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Disclaimer Text
            Text(
                text = "This amount is estimated, this will vary if you change your location or weight.",
                style = MaterialTheme.typography.bodySmall,
                color = SuccessDisclaimerColor,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 24.dp)
            )
        }

        // Button at the bottom
        Button(
            onClick = onBackToHomeClick,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 32.dp), // Ensure some space if content above is short
            shape = MaterialTheme.shapes.extraLarge, // For highly rounded corners
            colors = ButtonDefaults.buttonColors(
                containerColor = ShipmentsBrown,
                contentColor = Color.White
            ),
            contentPadding = PaddingValues(vertical = 16.dp)
        ) {
            Text(
                text = "Back to home",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}

@Preview(showBackground = true, device = "spec:width=1080px,height=2340px,dpi=440")
@Composable
fun SuccessScreenPreview() {
    MaterialTheme { // Wrap with your app's theme
        SuccessScreen()
    }
}