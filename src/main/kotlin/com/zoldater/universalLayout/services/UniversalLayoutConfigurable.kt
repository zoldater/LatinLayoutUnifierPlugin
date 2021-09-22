package com.zoldater.universalLayout.services

import com.intellij.openapi.options.Configurable
import com.zoldater.universalLayout.PluginSettings
import com.zoldater.universalLayout.util.SupportedLatinLanguage
import javax.swing.JComponent

class UniversalLayoutConfigurable : Configurable {
    private lateinit var component: PluginSettings

    override fun createComponent(): JComponent? {
        component = PluginSettings()
        return component.panel
    }

    override fun isModified(): Boolean {
        val layoutSettings = UniversalLayoutSettings.getInstance()
        return component.isCtrlSpecialKeyEnabled != layoutSettings.isCtrlSelected
                || component.isAltSpecialKeyEnabled != layoutSettings.isAltSelected
                || component.isMetaSpecialKeyEnabled != layoutSettings.isMetaSelected
                || component.isShiftCapitalLetterMode != layoutSettings.isShiftCapitalLetterMode
                || component.isCircleCapitalLetterMode != layoutSettings.isCircleCapitalLetterMode
                || component.selectedLanguage.toString().toUpperCase() != layoutSettings.selectedLanguage.name
    }

    override fun apply() {
        with(UniversalLayoutSettings.getInstance()) {
            selectedLanguage = SupportedLatinLanguage.valueOf(component.selectedLanguage.toString().toUpperCase())
            isCircleCapitalLetterMode = component.isCircleCapitalLetterMode
            isShiftCapitalLetterMode = component.isShiftCapitalLetterMode
            isCtrlSelected = component.isCtrlSpecialKeyEnabled
            isAltSelected = component.isAltSpecialKeyEnabled
            isMetaSelected = component.isMetaSpecialKeyEnabled
        }
    }

    override fun reset() {
        super.reset()
        with(UniversalLayoutSettings.getInstance()) {
            component.selectedLanguage = selectedLanguage.name.capitalize()
            component.isCircleCapitalLetterMode = isCircleCapitalLetterMode
            component.isShiftCapitalLetterMode = isShiftCapitalLetterMode
            component.isCtrlSpecialKeyEnabled = isCtrlSelected
            component.isAltSpecialKeyEnabled = isAltSelected
            component.isMetaSpecialKeyEnabled = isMetaSelected
        }
    }

    override fun getDisplayName(): String {
        TODO("Not yet implemented")
    }
}