package com.zoldater.universalLayout.services

import com.intellij.ide.IdeEventQueue
import com.intellij.openapi.components.Service
import com.intellij.openapi.components.service
import com.intellij.openapi.diagnostic.Logger
import com.zoldater.universalLayout.settings.UniversalLayoutSettings
import com.zoldater.universalLayout.substitution.ExtraSymbolsSubmitter
import com.zoldater.universalLayout.substitution.SubmitterMode
import com.zoldater.universalLayout.substitution.UniversalLayoutSubstitutionDispatcher
import com.zoldater.universalLayout.substitution.UniversalLayoutSubstitutionDispatcher.Companion.createDispatcher
import java.awt.AWTEvent
import java.awt.event.KeyEvent
import java.util.concurrent.atomic.AtomicBoolean

@Service(value = [Service.Level.APP])
class UniversalLayoutSubstitutionService : IdeEventQueue.EventDispatcher {
    private val settings: UniversalLayoutSettings by lazy { service() }
    private lateinit var sessionDispatchers: ArrayList<UniversalLayoutSubstitutionDispatcher>
    private var isActiveSession: Boolean = false
    private var isInitialized: Boolean = false

    @Synchronized
    fun initializeIfNeeded() {
        if (isInitialized) return
        IdeEventQueue.getInstance().addDispatcher(this, null)
        isInitialized = true
    }


    override fun dispatch(e: AWTEvent): Boolean {
        val keyEvent = e as? KeyEvent ?: return false
        return when {
            keyEvent.readyToStart -> onStartSession(e)
            keyEvent.readyToReset -> onResetSession()
            else -> false
        }
    }

    private val KeyEvent.readyToStart: Boolean
        get() = id == KeyEvent.KEY_PRESSED && keyChar == KeyEvent.CHAR_UNDEFINED && isAcceptable

    private val KeyEvent.readyToReset: Boolean
        get() = id == KeyEvent.KEY_RELEASED && keyChar == KeyEvent.CHAR_UNDEFINED && !isAcceptable

    //TODO predicate for editor only
    private val KeyEvent.isAcceptable: Boolean
        get() = with(settings) { isAltSelected == isAltDown && isCtrlSelected == isControlDown && isMetaSelected == isMetaDown }

    private val KeyEvent.computeSubmitterMode: SubmitterMode
        get() = when {
            isShiftDown -> SubmitterMode.CAPITAL_ONLY
            settings.isCircleCapitalLetterMode -> SubmitterMode.ALL
            else -> SubmitterMode.SMALL_ONLY
        }


    private fun onStartSession(e: KeyEvent): Boolean {
        if (isActiveSession) {
            return false
        }
        isActiveSession = true
        sessionDispatchers = arrayListOf<UniversalLayoutSubstitutionDispatcher>().apply {
            val dispatchers =
                ExtraSymbolsSubmitter.submitterForLanguage(settings.selectedLanguage, e.computeSubmitterMode)
                    .submitExtraSymbols()
                    .entries
                    .map { (origin, extras) -> createDispatcher(origin, extras) }
            addAll(dispatchers)
        }
        sessionDispatchers.forEach { IdeEventQueue.getInstance().addDispatcher(it, null) }
        return true
    }

    private fun onResetSession(): Boolean {
        if (!isActiveSession) {
            return false
        }
        sessionDispatchers.forEach {
            IdeEventQueue.getInstance().removeDispatcher(it)
            it.cleanSessionState()
        }
        sessionDispatchers.clear()
        isActiveSession = false
        return true
    }

    companion object {
        private val LOGGER = Logger.getInstance(UniversalLayoutSubstitutionService::class.java)
        fun getInstance(): UniversalLayoutSubstitutionService = service()
    }
}