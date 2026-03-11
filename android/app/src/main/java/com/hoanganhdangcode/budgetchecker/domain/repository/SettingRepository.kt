package com.hoanganhdangcode.budgetchecker.domain.repository

interface SettingRepository {
    fun getTheme(): String?

    fun setTheme(theme: String)

    fun getLanguage(): String?

    fun setLanguage(language: String)
}
