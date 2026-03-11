package com.hoanganhdangcode.budgetchecker.data.local

import com.hoanganhdangcode.budgetchecker.domain.model.LoggedUser

interface AuthLocalDataSource {
    suspend fun saveUser(user: LoggedUser)

    suspend fun getSavedUser(): LoggedUser?

    suspend fun clearUser()
}
