package com.hoanganhdangcode.budgetchecker.data.repository

import android.content.SharedPreferences
import com.hoanganhdangcode.budgetchecker.core.constant.StaticConstant
import com.hoanganhdangcode.budgetchecker.domain.repository.SettingRepository

class SettingRepositoryImp(private val settingDataSource: SharedPreferences): SettingRepository {

    private fun commonGetString(key: String, defVal: String?): String? =
        settingDataSource.getString(key, defVal)

    private fun commonSetString(key: String, value: String) =
        settingDataSource.edit().putString(key, value).apply()





    override fun getTheme(): String? =  commonGetString(StaticConstant.THEME, null)
    override fun setTheme(theme: String) = commonSetString(StaticConstant.THEME, theme)

    override fun getLanguage(): String?  = commonGetString(StaticConstant.LANGUAGE, null)
    override fun setLanguage(language: String) = commonSetString(StaticConstant.LANGUAGE, language)
}
