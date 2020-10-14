package com.superddaiupay

import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.ColorFilter
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import androidx.appcompat.widget.SwitchCompat
import androidx.core.graphics.drawable.DrawableCompat
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.roundToInt

object Utils {

    @JvmStatic
    fun minDecimalFormat(value: Number?, decimalDigits: Int): String {
        val nf = NumberFormat.getInstance(Locale("pt", "BR"))
        nf.minimumFractionDigits = decimalDigits
        return nf.format(value?.toDouble() ?: 0)
    }

    @JvmStatic
    fun maxDecimalFormat(value: Number?, decimalDigits: Int): String {
        val nf = NumberFormat.getInstance(Locale("pt", "BR"))
        nf.maximumFractionDigits = decimalDigits
        return nf.format(value?.toDouble() ?: 0)
    }

    @JvmStatic
    fun formatMoney(value: Number?): String {
        return minDecimalFormat(value, 2)
    }

    @JvmStatic
    fun formatText(value: String?): String {
        return value ?: ""
    }

    @JvmStatic
    fun formatDate(date: Date?, pattern: String): String {
        return date?.let {
            SimpleDateFormat(pattern, Locale.getDefault())
                .format(it).toUpperCase(Locale.ROOT)
        } ?: ""
    }

    @JvmStatic
    fun parseColorFilter(color: Int): ColorFilter {
        return PorterDuffColorFilter(
            color,
            PorterDuff.Mode.SRC_ATOP
        )
    }

    @JvmStatic
    fun parseColorFilter(color: String): ColorFilter {
        return PorterDuffColorFilter(
            Color.parseColor(color),
            PorterDuff.Mode.SRC_ATOP
        )
    }

    @JvmStatic
    fun getColorWithAlpha(color: Int, ratio: Float): Int {
        val alpha = (Color.alpha(color) * ratio).roundToInt()
        val r = Color.red(color)
        val g = Color.green(color)
        val b = Color.blue(color)
        return Color.argb(alpha, r, g, b)
    }

    @JvmStatic
    fun setSwitchColor(
        switch: SwitchCompat,
        circleActiveColor: Int = Color.parseColor("#f78733"),
        circleInactiveColor: Int = Color.parseColor("#717171")
    ) {
        val backgroundActive = getColorWithAlpha(circleActiveColor, 90f)
        val backgroundInactive = getColorWithAlpha(circleInactiveColor, 90f)
        DrawableCompat.setTintList(
            switch.trackDrawable, ColorStateList(
                arrayOf(intArrayOf(android.R.attr.state_checked), intArrayOf()), intArrayOf(
                    backgroundActive,
                    backgroundInactive // 30% transparency (4D)
                )
            )
        )
        DrawableCompat.setTintList(
            switch.thumbDrawable, ColorStateList(
                arrayOf(intArrayOf(android.R.attr.state_checked), intArrayOf()), intArrayOf(
                    circleActiveColor,
                    circleInactiveColor
                )
            )
        )
    }
}