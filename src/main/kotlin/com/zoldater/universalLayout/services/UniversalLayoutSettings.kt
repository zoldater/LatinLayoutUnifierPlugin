package com.zoldater.universalLayout.services

import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.components.ServiceManager
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage
import com.intellij.util.xmlb.XmlSerializerUtil
import com.zoldater.universalLayout.util.*

@State(name = SETTINGS_SECTION, storages = [Storage(value = SETTINGS_FILE)])
class UniversalLayoutSettings : PersistentStateComponent<UniversalLayoutSettings> {
    var selectedLanguage: SupportedLatinLanguage = SupportedLatinLanguage.UNIVERSAL
    var isCircleCapitalLetterMode: Boolean = true
    var isShiftCapitalLetterMode: Boolean = false
    var isCtrlSelected: Boolean = false
    var isAltSelected: Boolean = true
    var isMetaSelected: Boolean = false

    override fun getState(): UniversalLayoutSettings? {
        return this
    }

    override fun loadState(state: UniversalLayoutSettings) {
        XmlSerializerUtil.copyBean(state, this)
    }

    companion object {
        fun getInstance(): UniversalLayoutSettings = ServiceManager.getService(UniversalLayoutSettings::class.java)
    }
}