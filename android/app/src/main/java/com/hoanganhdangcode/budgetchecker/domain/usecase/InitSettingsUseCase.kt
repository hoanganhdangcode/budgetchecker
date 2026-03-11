package com.hoanganhdangcode.budgetchecker.domain.usecase

import com.hoanganhdangcode.budgetchecker.core.constant.StaticConstant
import com.hoanganhdangcode.budgetchecker.core.constant.ThemeConstant
import com.hoanganhdangcode.budgetchecker.domain.repository.SettingRepository
import java.util.Locale

class InitSettingsUseCase(
    private val settingRepository: SettingRepository,
) {
    operator fun invoke() {
        if (settingRepository.getTheme() == null) {
            settingRepository.setTheme(ThemeConstant.THEME_SYSTEM)
        }
    }
}
