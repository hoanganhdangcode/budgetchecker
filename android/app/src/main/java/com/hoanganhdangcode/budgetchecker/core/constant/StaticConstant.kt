package com.hoanganhdangcode.budgetchecker.core.constant

import androidx.datastore.preferences.core.stringPreferencesKey

object StaticConstant {
    const val APP_SETTINGS = "app_settings"
    const val THEME = "theme"
    const val LANGUAGE = "language"

    const val AUTH_PREFS = "auth_prefs"
    val KEY_USER_JSON = stringPreferencesKey("logged_user_json")
}
