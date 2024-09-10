package com.felipeschoffen.montrabudgetapp.core.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.googlefonts.GoogleFont
import androidx.compose.ui.unit.sp
import com.felipeschoffen.montrabudgetapp.R


val provider = GoogleFont.Provider(
    providerAuthority = "com.google.android.gms.fonts",
    providerPackage = "com.google.android.gms",
    certificates = R.array.com_google_android_gms_fonts_certs
)

val interFontFamilySemiBold = FontFamily(
    Font(
        googleFont = GoogleFont("Inter"),
        fontProvider = provider,
        weight = FontWeight.SemiBold
    )
)

val interFontFamilyBold = FontFamily(
    Font(
        googleFont = GoogleFont("Inter"),
        fontProvider = provider,
        weight = FontWeight.Bold
    )
)

val interFontFamilyNormal = FontFamily(
    Font(
        googleFont = GoogleFont("Inter"),
        fontProvider = provider
    )
)

// Set of Material typography styles to start with
val Typography = Typography(
    titleLarge = TextStyle(
        fontFamily = interFontFamilyBold,
        fontSize = 64.sp,
        lineHeight = 80.sp,
    ),
    titleMedium = TextStyle(
        fontFamily = interFontFamilyBold,
        fontSize = 32.sp,
        lineHeight = 34.sp,
    ) ,
    titleSmall = TextStyle(
        fontFamily = interFontFamilyBold,
        fontSize = 18.sp,
        lineHeight = 22.sp,
    ),
    bodyLarge = TextStyle(
        fontFamily = interFontFamilySemiBold,
        fontSize = 16.sp,
        lineHeight = 19.sp,
    ),
    bodyMedium = TextStyle(
        fontFamily = interFontFamilyNormal,
        fontSize = 16.sp,
        lineHeight = 19.sp,
    ),
    bodySmall = TextStyle(
        fontFamily = interFontFamilyNormal,
        fontSize = 14.sp,
        lineHeight = 18.sp,
    ),
    labelSmall = TextStyle(
        fontFamily = interFontFamilyNormal,
        fontSize = 13.sp,
        lineHeight = 16.sp,
    ),
    displaySmall = TextStyle(
        fontFamily = interFontFamilyNormal,
        fontSize = 12.sp,
        lineHeight = 12.sp,
    )
)
