package com.hoanganhdangcode.budgetchecker.data.local

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.google.gson.Gson
import com.hoanganhdangcode.budgetchecker.core.constant.StaticConstant
import com.hoanganhdangcode.budgetchecker.core.constant.StaticConstant.KEY_USER_JSON
import com.hoanganhdangcode.budgetchecker.domain.model.LoggedUser
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(name = StaticConstant.AUTH_PREFS)

class AuthLocalDataSourceImp(
    private val context: Context,
) : AuthLocalDataSource {
    private val gson = Gson()

    override suspend fun saveUser(user: LoggedUser) {
        context.dataStore.edit { preferences ->
            val jsonString = gson.toJson(user)
            preferences[KEY_USER_JSON] = jsonString
        }
    }

    override suspend fun getSavedUser(): LoggedUser? {
        val jsonString =
            context.dataStore.data
                .map { preferences ->
                    preferences[KEY_USER_JSON]
                }.first()

        return jsonString?.let {
            try {
                gson.fromJson(it, LoggedUser::class.java)
            } catch (e: Exception) {
                null
            }
        }
    }

    override suspend fun clearUser() {
        context.dataStore.edit { preferences ->
            preferences.remove(KEY_USER_JSON)
        }
    }
}
