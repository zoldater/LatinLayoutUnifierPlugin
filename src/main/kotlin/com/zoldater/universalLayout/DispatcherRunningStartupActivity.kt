package com.zoldater.universalLayout

import com.intellij.openapi.application.PreloadingActivity
import com.intellij.openapi.progress.ProgressIndicator
import com.zoldater.universalLayout.services.UniversalLayoutSubstitutionService

class DispatcherRunningStartupActivity : PreloadingActivity() {
    override fun preload(indicator: ProgressIndicator) {
        UniversalLayoutSubstitutionService.getInstance().initializeIfNeeded()
    }
}