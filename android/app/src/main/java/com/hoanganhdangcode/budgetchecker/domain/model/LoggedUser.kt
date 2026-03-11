package com.hoanganhdangcode.budgetchecker.domain.model

data class LoggedUser (
    var userid:String?,
    var name:String?,
    var avatarUrl:String?,
    var refreshToken:String?,
    var accessToken:String?,
    var permission: UserPermissionEnum,

)
enum class UserPermissionEnum(val value: Int)
{
    BLOCK(-1),
    NOT_ACTIVE(0),
    ACTIVE(1),
    ADMIN(2);

    companion object {
        fun fromInt(value: Int) = values().first { it.value == value }
        fun fromString(value: String) = values().first { it.name == value }
    }
}

