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
import androidx.compose.ui.res.stringResource
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
import com.cmaina.shipments.ui.theme.LogoTextColor
import com.cmaina.shipments.ui.theme.ShipmentsBrown
import com.cmaina.shipments.ui.theme.ShipmentsPurple
import com.cmaina.shipments.ui.theme.ShipmentsSmokeWhite
import com.cmaina.shipments.ui.theme.SuccessAmountColor
import com.cmaina.shipments.ui.theme.SuccessButtonBackgroundColor
import com.cmaina.shipments.ui.theme.SuccessCurrencyColor
import com.cmaina.shipments.ui.theme.SuccessDisclaimerColor


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
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            // Logo Section
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = stringResource(R.string.movemate),
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = LogoTextColor
                )
                Spacer(modifier = Modifier.width(8.dp))
                Icon(
                    imageVector = Icons.Filled.LocalShipping,
                    contentDescription = "Logo Truck",
                    tint = SuccessButtonBackgroundColor,
                    modifier = Modifier.size(32.dp)
                )
            }

            Spacer(modifier = Modifier.height(48.dp))

            // Package Image
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = "Package Illustration",
                modifier = Modifier.size(180.dp)
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Title
            Text(
                text = stringResource(R.string.total_estimated_amount),
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
                            fontSize = 48.sp,
                            fontWeight = FontWeight.Bold
                        )
                    ) {
                        append("$$estimatedAmount")
                    }
                    append(" ")
                    withStyle(
                        style = SpanStyle(
                            color = SuccessCurrencyColor,
                            fontSize = 20.sp,
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
                text = stringResource(R.string.this_amount_is_estimated_this_will_vary_if_you_change_your_location_or_weight),
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
                .padding(top = 32.dp),
            shape = MaterialTheme.shapes.extraLarge,
            colors = ButtonDefaults.buttonColors(
                containerColor = ShipmentsBrown,
                contentColor = Color.White
            ),
            contentPadding = PaddingValues(vertical = 16.dp)
        ) {
            Text(
                text = stringResource(R.string.back_to_home),
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}

@Preview(showBackground = true, device = "spec:width=1080px,height=2340px,dpi=440")
@Composable
fun SuccessScreenPreview() {
    MaterialTheme {
        SuccessScreen()
    }
}