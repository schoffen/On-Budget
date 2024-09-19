package com.felipeschoffen.montrabudgetapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.felipeschoffen.montrabudgetapp.core.ui.theme.MontraBudgetAppTheme
import com.felipeschoffen.montrabudgetapp.pin.ui.PinInputScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MontraBudgetAppTheme {
                var pin by remember { mutableStateOf("") }

                PinInputScreen(
                    pin = pin,
                    onPinChange = { pin = it },
                    onSend = {}
                )
            }
        }
    }
}