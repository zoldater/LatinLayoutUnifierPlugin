package com.zoldater.universalLayout.substitution

import com.zoldater.universalLayout.util.SupportedLatinLanguage
import com.zoldater.universalLayout.util.SupportedLatinLanguage.*

enum class SubmitterMode {
    SMALL_ONLY, CAPITAL_ONLY, ALL
}

sealed class ExtraSymbolsSubmitter(val submitterMode: SubmitterMode) {
    protected abstract val additionalSymbolsMap: Map<Char, Set<Char>>
    fun submitExtraSymbols(): Map<Char, Set<Char>> = when (submitterMode) {
        SubmitterMode.SMALL_ONLY -> additionalSymbolsMap
        SubmitterMode.CAPITAL_ONLY -> additionalSymbolsMap
            .map { (k, v) -> k.toUpperCase() to v.map { it.toUpperCase() }.toSet() }.toMap()
        SubmitterMode.ALL -> additionalSymbolsMap.mapValues { (_, v) -> v + v.map { it.toUpperCase() }.toSet() }
    }

    companion object Factory {
        fun submitterForLanguage(language: SupportedLatinLanguage, mode: SubmitterMode): ExtraSymbolsSubmitter =
            when (language) {
                NONE -> Default(mode)
                UNIVERSAL -> Universal(mode)
                CZECH -> Czech(mode)
                DANISH -> Danish(mode)
                DUTCH -> Dutch(mode)
                ESPERANTO -> Esperanto(mode)
                FINNISH -> Finnish(mode)
                FRENCH -> French(mode)
                GERMAN -> German(mode)
                HUNGARIAN -> Hungarian(mode)
                ICELANDIC -> Icelandic(mode)
                ITALIAN -> Italian(mode)
                MAORI -> Maori(mode)
                NORWEGIAN -> Norwegian(mode)
                POLISH -> Polish(mode)
                PORTUGUESE -> Portuguese(mode)
                ROMANIAN -> Romanian(mode)
                SPANISH -> Spanish(mode)
                SWEDISH -> Swedish(mode)
            }

        private class Default(mode: SubmitterMode) : ExtraSymbolsSubmitter(mode) {
            override val additionalSymbolsMap: Map<Char, Set<Char>> by lazy { emptyMap() }
        }

        private class Universal(mode: SubmitterMode) : ExtraSymbolsSubmitter(mode) {
            override val additionalSymbolsMap: Map<Char, Set<Char>> by lazy {
                hashMapOf<Char, HashSet<Char>>().apply {
                    listOf(
                        Czech(mode),
                        Danish(mode),
                        Dutch(mode),
                        Esperanto(mode),
                        Finnish(mode),
                        French(mode),
                        German(mode),
                        Hungarian(mode),
                        Icelandic(mode),
                        Italian(mode),
                        Maori(mode),
                        Norwegian(mode),
                        Polish(mode),
                        Portuguese(mode),
                        Romanian(mode),
                        Spanish(mode),
                        Swedish(mode)
                    ).flatMap { it.additionalSymbolsMap.entries }
                        .forEach { get(it.key)?.addAll(it.value) ?: put(it.key, hashSetOf(*it.value.toTypedArray())) }
                }
            }
        }
    }

    private class Czech(mode: SubmitterMode) : ExtraSymbolsSubmitter(mode) {
        override val additionalSymbolsMap: Map<Char, Set<Char>> by lazy {
            hashMapOf(
                'a' to sortedSetOf('\u00E1'),
                'c' to sortedSetOf('\u010D'),
                'd' to sortedSetOf('\u010F'),
                'e' to sortedSetOf('\u00E9', '\u011B'),
                'i' to sortedSetOf('\u00ED'),
                'n' to sortedSetOf('\u0148'),
                'o' to sortedSetOf('\u00F3'),
                'r' to sortedSetOf('\u0159'),
                's' to sortedSetOf('\u0161'),
                't' to sortedSetOf('\u0165'),
                'u' to sortedSetOf('\u00FA', '\u016F'),
                'y' to sortedSetOf('\u00FD'),
                'z' to sortedSetOf('\u017E'),
            )
        }
    }

    private class Danish(mode: SubmitterMode) : ExtraSymbolsSubmitter(mode) {
        override val additionalSymbolsMap: Map<Char, Set<Char>> by lazy {
            hashMapOf(
                'a' to sortedSetOf('\u00E5', '\u00E6'),
                'e' to sortedSetOf('\u00E9'),
                'o' to sortedSetOf('\u00F8'),
            )
        }
    }

    private class Dutch(mode: SubmitterMode) : ExtraSymbolsSubmitter(mode) {
        override val additionalSymbolsMap: Map<Char, Set<Char>> by lazy {
            hashMapOf(
                'e' to sortedSetOf('\u00E9', '\u00EB'),
                'i' to sortedSetOf('\u00EF'),
                'o' to sortedSetOf('\u00F3', '\u00F6'),
                'u' to sortedSetOf('\u00FC'),
            )
        }
    }

    private class Esperanto(mode: SubmitterMode) : ExtraSymbolsSubmitter(mode) {
        override val additionalSymbolsMap: Map<Char, Set<Char>> by lazy {
            hashMapOf(
                'c' to sortedSetOf('\u0109'),
                'g' to sortedSetOf('\u011D'),
                'h' to sortedSetOf('\u0125'),
                'j' to sortedSetOf('\u0135'),
                's' to sortedSetOf('\u015D'),
                'u' to sortedSetOf('\u00EF'),
            )
        }
    }

    private class Finnish(mode: SubmitterMode) : ExtraSymbolsSubmitter(mode) {
        override val additionalSymbolsMap: Map<Char, Set<Char>> by lazy {
            hashMapOf(
                'a' to sortedSetOf('\u00E4', '\u00E5'),
                'o' to sortedSetOf('\u00F6'),
            )
        }
    }

    private class French(mode: SubmitterMode) : ExtraSymbolsSubmitter(mode) {
        override val additionalSymbolsMap: Map<Char, Set<Char>> by lazy {
            hashMapOf(
                'a' to sortedSetOf('\u00E0', '\u00E2', '\u00E6'),
                'c' to sortedSetOf('\u00E7'),
                'e' to sortedSetOf('\u00E9', '\u00E8', '\u00EA', '\u00EB'),
                'i' to sortedSetOf('\u00EF', '\u00EE'),
                'o' to sortedSetOf('\u00F4', '\u0153'),
                'u' to sortedSetOf('\u00F9', '\u00FB', '\u00FC'),
                'y' to sortedSetOf('\u00FF'),
            )
        }
    }

    private class German(mode: SubmitterMode) : ExtraSymbolsSubmitter(mode) {
        override val additionalSymbolsMap: Map<Char, Set<Char>> by lazy {
            hashMapOf(
                'a' to sortedSetOf('\u00E4'),
                'o' to sortedSetOf('\u00F6'),
                's' to sortedSetOf('\u00DF'),
                'u' to sortedSetOf('\u00FC'),
            )
        }
    }

    private class Hungarian(mode: SubmitterMode) : ExtraSymbolsSubmitter(mode) {
        override val additionalSymbolsMap: Map<Char, Set<Char>> by lazy {
            hashMapOf(
                'a' to sortedSetOf('\u00E1'),
                'e' to sortedSetOf('\u00E9'),
                'i' to sortedSetOf('\u00ED'),
                'o' to sortedSetOf('\u00F6', '\u00F3', '\u0151'),
                'u' to sortedSetOf('\u00FC', '\u00FA', '\u0171'),
            )
        }
    }

    private class Italian(mode: SubmitterMode) : ExtraSymbolsSubmitter(mode) {
        override val additionalSymbolsMap: Map<Char, Set<Char>> by lazy {
            hashMapOf(
                'a' to sortedSetOf('\u00E0'),
                'e' to sortedSetOf('\u00E8', '\u00E9'),
                'i' to sortedSetOf('\u00EC'),
                'o' to sortedSetOf('\u00F2', '\u00F3'),
                'u' to sortedSetOf('\u00F9'),
            )
        }
    }

    private class Icelandic(mode: SubmitterMode) : ExtraSymbolsSubmitter(mode) {
        override val additionalSymbolsMap: Map<Char, Set<Char>> by lazy {
            hashMapOf(
                'a' to sortedSetOf('\u00E1', '\u00E6'),
                'd' to sortedSetOf('\u00F0'),
                'e' to sortedSetOf('\u00E9'),
                'i' to sortedSetOf('\u00ED'),
                'o' to sortedSetOf('\u00F3', '\u00F6'),
                't' to sortedSetOf('\u00FE'),
                'u' to sortedSetOf('\u00FA'),
                'y' to sortedSetOf('\u00FD'),
            )
        }
    }

    private class Maori(mode: SubmitterMode) : ExtraSymbolsSubmitter(mode) {
        override val additionalSymbolsMap: Map<Char, Set<Char>> by lazy {
            hashMapOf(
                'a' to sortedSetOf('\u0101'),
                'e' to sortedSetOf('\u0113'),
                'i' to sortedSetOf('\u012B'),
                'o' to sortedSetOf('\u014D'),
                'u' to sortedSetOf('\u016B'),
            )
        }
    }

    private class Norwegian(mode: SubmitterMode) : ExtraSymbolsSubmitter(mode) {
        override val additionalSymbolsMap: Map<Char, Set<Char>> by lazy {
            hashMapOf(
                'a' to sortedSetOf('\u00E5', '\u00E6'),
                'o' to sortedSetOf('\u00F8'),
            )
        }
    }

    private class Polish(mode: SubmitterMode) : ExtraSymbolsSubmitter(mode) {
        override val additionalSymbolsMap: Map<Char, Set<Char>> by lazy {
            hashMapOf(
                'a' to sortedSetOf('\u0105'),
                'c' to sortedSetOf('\u0107'),
                'e' to sortedSetOf('\u0119'),
                'l' to sortedSetOf('\u0142'),
                'n' to sortedSetOf('\u0144'),
                'o' to sortedSetOf('\u00F3'),
                's' to sortedSetOf('\u015B'),
                'z' to sortedSetOf('\u017A', '\u017C'),
            )
        }
    }

    private class Portuguese(mode: SubmitterMode) : ExtraSymbolsSubmitter(mode) {
        override val additionalSymbolsMap: Map<Char, Set<Char>> by lazy {
            hashMapOf(
                'a' to sortedSetOf('\u00E3', '\u00E1', '\u00E0', '\u00E2'),
                'c' to sortedSetOf('\u00E7'),
                'e' to sortedSetOf('\u00E9', '\u00EA'),
                'i' to sortedSetOf('\u00ED'),
                'o' to sortedSetOf('\u00F5', '\u00F3', '\u00F4'),
                'u' to sortedSetOf('\u00FA', '\u00FC'),
            )
        }
    }

    private class Romanian(mode: SubmitterMode) : ExtraSymbolsSubmitter(mode) {
        override val additionalSymbolsMap: Map<Char, Set<Char>> by lazy {
            hashMapOf(
                'a' to sortedSetOf('\u0103', '\u00E2'),
                'i' to sortedSetOf('\u00EE'),
                's' to sortedSetOf('\u015F'),
                't' to sortedSetOf('\u0163'),
            )
        }
    }

    private class Spanish(mode: SubmitterMode) : ExtraSymbolsSubmitter(mode) {
        override val additionalSymbolsMap: Map<Char, Set<Char>> by lazy {
            hashMapOf(
                'a' to sortedSetOf('\u00E1'),
                'e' to sortedSetOf('\u00E9'),
                'i' to sortedSetOf('\u00ED'),
                'n' to sortedSetOf('\u00F1'),
                'o' to sortedSetOf('\u00F3'),
                'u' to sortedSetOf('\u00FA', '\u00FC'),
                '?' to sortedSetOf('\u00BF'),
                '!' to sortedSetOf('\u00A1'),
            )
        }
    }

    private class Swedish(mode: SubmitterMode) : ExtraSymbolsSubmitter(mode) {
        override val additionalSymbolsMap: Map<Char, Set<Char>> by lazy {
            hashMapOf(
                'a' to sortedSetOf('\u00E4', '\u00E5'),
                'o' to sortedSetOf('\u00F6'),
                's' to sortedSetOf('\u0161'),
                'z' to sortedSetOf('\u017E'),
            )
        }
    }
}