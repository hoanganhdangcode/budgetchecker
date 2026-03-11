package com.hoanganhdangcode.budgetchecker.domain.repository

import com.hoanganhdangcode.budgetchecker.domain.model.LoggedUser

interface AuthRepository {

    /**
     * Lấy thông tin user hiện tại.
     * Logic ngầm (ở tầng Data): Ưu tiên lấy từ Cache (RAM), nếu RAM null thì lấy từ Encrypted Disk.
     * @return LoggedUser nếu đã đăng nhập, null nếu chưa.
     */
    fun getLoggedUser(): LoggedUser?

    /**
     * Lưu thông tin user sau khi gọi API Login thành công (hoặc khi refresh token).
     * Logic ngầm: Cập nhật biến Cache trên RAM và ghi file JSON xuống Disk.
     */
    fun saveLoggedUser(user: LoggedUser)

    /**
     * Dọn dẹp session.
     * Logic ngầm: Gán biến Cache = null và xóa file trong Disk.
     */
    fun logout()
}