package com.zoldater.universalLayout.substitution

import com.zoldater.universalLayout.util.SupportedLatinLanguage

data class UniversalLayoutSubstitution(val substitution: Char) : Comparable<UniversalLayoutSubstitution> {
    override fun compareTo(other: UniversalLayoutSubstitution): Int = substitution.compareTo(other.substitution)

    companion object {
        private val substitutionCache: HashMap<Char, UniversalLayoutSubstitution> = hashMapOf()
        fun Char.substitution(): UniversalLayoutSubstitution =
            substitutionCache.getOrPut(this) { UniversalLayoutSubstitution(this) }
    }
}
