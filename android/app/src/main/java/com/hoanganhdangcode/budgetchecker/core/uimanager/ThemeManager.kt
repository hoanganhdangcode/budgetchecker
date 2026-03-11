package com.hoanganhdangcode.budgetchecker.core.uimanager

import androidx.appcompat.app.AppCompatDelegate
import com.hoanganhdangcode.budgetchecker.core.constant.ThemeConstant
import com.hoanganhdangcode.budgetchecker.core.constant.ThemeConstant.THEME_DARK
import com.hoanganhdangcode.budgetchecker.core.constant.ThemeConstant.THEME_LIGHT

object ThemeManager {
    fun applyTheme(theme: String) {
        when (theme) {
            THEME_LIGHT -> {
                AppCompatDelegate.setDefaultNightMode(
                    AppCompatDelegate.MODE_NIGHT_NO,
                )
            }

            THEME_DARK -> {
                AppCompatDelegate.setDefaultNightMode(
                    AppCompatDelegate.MODE_NIGHT_YES,
                )
            }

            else -> {
                AppCompatDelegate.setDefaultNightMode(
                    AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM,
                )
            }
        }
    }
}
