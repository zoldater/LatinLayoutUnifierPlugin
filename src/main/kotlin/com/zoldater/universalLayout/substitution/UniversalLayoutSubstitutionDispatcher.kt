package com.zoldater.universalLayout.substitution

import com.intellij.ide.IdeEventQueue
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.command.WriteCommandAction
import com.intellij.openapi.diagnostic.Logger
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.editor.EditorFactory
import java.awt.AWTEvent
import java.awt.Component
import java.awt.event.KeyEvent

data class UniversalLayoutSubstitutionDispatcher(private val latin: Char, private val substitutions: Set<Char>) :
    IdeEventQueue.EventDispatcher {
    private val skippedSubstitutions: ArrayList<Char> = ArrayList(substitutions.size)
    private var currentSessionEditor: Editor? = null
    private var deleteBeforeAdd: Boolean = false

    override fun dispatch(e: AWTEvent): Boolean {
        val keyEvent = e as? KeyEvent ?: return false
        if (keyEvent.id == KeyEvent.KEY_RELEASED) return false

        if (!keyEvent.matches(latin)) {
            return cleanSessionState()
        }
        //Assume it's OK for now
        val editor = currentSessionEditor ?: EditorFactory.getInstance().allEditors.firstOrNull()
        if (editor != null) {
            currentSessionEditor = editor
            var offset = editor.caretModel.offset
            if (deleteBeforeAdd) {
                WriteCommandAction.runWriteCommandAction(editor.project) {
                    editor.document.deleteString(offset - 1, offset)
                    offset -= 1
                }
            }
            val substitution = if (skippedSubstitutions.size < substitutions.size)
                substitutions.first { it !in skippedSubstitutions }.also { skippedSubstitutions.add(it) }
            else {
                skippedSubstitutions.clear()
                latin
            }

            WriteCommandAction.runWriteCommandAction(editor.project) {
                editor.document.insertString(offset, "$substitution")
                deleteBeforeAdd = true
                editor.caretModel.moveToOffset(offset + 1)
            }
            return true
        }
        return false
    }

    fun cleanSessionState(): Boolean {
        skippedSubstitutions.clear()
        deleteBeforeAdd = false
        currentSessionEditor = null
        return false
    }

    companion object {
        private val LOGGER = Logger.getInstance(UniversalLayoutSubstitutionDispatcher::class.java)

        private val substitutionCache: HashMap<Char, UniversalLayoutSubstitutionDispatcher> = hashMapOf()

        fun createDispatcher(latin: Char, extras: Set<Char>): UniversalLayoutSubstitutionDispatcher =
            UniversalLayoutSubstitutionDispatcher(latin, extras)
    }

    private val KeyEvent.text: String
        get() = KeyEvent.getKeyText(keyCode)

    private fun KeyEvent.matches(symbol: Char): Boolean =
        text.equals(symbol.toString(), ignoreCase = true)
}
