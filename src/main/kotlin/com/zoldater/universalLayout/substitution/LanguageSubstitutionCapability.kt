package com.zoldater.universalLayout.substitution

import com.zoldater.universalLayout.substitution.UniversalLayoutSubstitution.Companion.substitution
import com.zoldater.universalLayout.util.SupportedLatinLanguage
import com.zoldater.universalLayout.util.SupportedLatinLanguage.*

sealed class LanguageSubstitutionCapability {
    protected abstract val substitutionsBySymbolsMap: Map<Char, Set<UniversalLayoutSubstitution>>
    fun collectSubstitutionsForSymbol(base: Char): Set<UniversalLayoutSubstitution> =
        substitutionsBySymbolsMap.getOrDefault(base, emptySet())


    companion object {
        val Factory = object {
            fun capabilityForLanguage(language: SupportedLatinLanguage): LanguageSubstitutionCapability =
                when (language) {
                    UNIVERSAL -> Universal
                    CZECH -> Czech
                    DANISH -> Danish
                    DUTCH -> Dutch
                    ESPERANTO -> Esperanto
                    FINNISH -> Finnish
                    FRENCH -> French
                    GERMAN -> German
                    HUNGARIAN -> Hungarian
                    ICELANDIC -> Icelandic
                    ITALIAN -> Italian
                    MAORI -> Maori
                    NORWEGIAN -> Norwegian
                    POLISH -> Polish
                    PORTUGUESE -> Portuguese
                    ROMANIAN -> Romanian
                    SPANISH -> Spanish
                    SWEDISH -> Swedish
                }
        }

        private object Universal : LanguageSubstitutionCapability() {
            override val substitutionsBySymbolsMap: Map<Char, Set<UniversalLayoutSubstitution>> =
                hashMapOf<Char, Set<UniversalLayoutSubstitution>>().apply {
                    listOf(
                        Czech,
                        Danish,
                        Dutch,
                        Esperanto,
                        Finnish,
                        French,
                        German,
                        Hungarian,
                        Icelandic,
                        Italian,
                        Maori,
                        Norwegian,
                        Polish,
                        Portuguese,
                        Romanian,
                        Spanish,
                        Swedish
                    ).flatMap { it.substitutionsBySymbolsMap.entries }
                        .forEach {
                            put(it.key, getOrPut(it.key) { hashSetOf() } + it.value)
                        }
                }
        }

        private object Czech : LanguageSubstitutionCapability() {
            override val substitutionsBySymbolsMap: Map<Char, Set<UniversalLayoutSubstitution>> by lazy {
                hashMapOf(
                    'a' to sortedSetOf('\u00E1'.substitution()),
                    'c' to sortedSetOf('\u010D'.substitution()),
                    'd' to sortedSetOf('\u010F'.substitution()),
                    'e' to sortedSetOf('\u00E9'.substitution(), '\u011B'.substitution()),
                    'i' to sortedSetOf('\u00ED'.substitution()),
                    'n' to sortedSetOf('\u0148'.substitution()),
                    'o' to sortedSetOf('\u00F3'.substitution()),
                    'r' to sortedSetOf('\u0159'.substitution()),
                    's' to sortedSetOf('\u0161'.substitution()),
                    't' to sortedSetOf('\u0165'.substitution()),
                    'u' to sortedSetOf('\u00FA'.substitution(), '\u016F'.substitution()),
                    'y' to sortedSetOf('\u00FD'.substitution()),
                    'z' to sortedSetOf('\u017E'.substitution()),
                )
            }
        }

        private object Danish : LanguageSubstitutionCapability() {
            override val substitutionsBySymbolsMap: Map<Char, Set<UniversalLayoutSubstitution>> by lazy {
                hashMapOf(
                    'a' to sortedSetOf('\u00E5'.substitution(), '\u00E6'.substitution()),
                    'e' to sortedSetOf('\u00E9'.substitution()),
                    'o' to sortedSetOf('\u00F8'.substitution()),
                )
            }
        }

        private object Dutch : LanguageSubstitutionCapability() {
            override val substitutionsBySymbolsMap: Map<Char, Set<UniversalLayoutSubstitution>> by lazy {
                hashMapOf(
                    'e' to sortedSetOf('\u00E9'.substitution(), '\u00EB'.substitution()),
                    'i' to sortedSetOf('\u00EF'.substitution()),
                    'o' to sortedSetOf('\u00F3'.substitution(), '\u00F6'.substitution()),
                    'u' to sortedSetOf('\u00FC'.substitution()),
                )
            }
        }

        private object Esperanto : LanguageSubstitutionCapability() {
            override val substitutionsBySymbolsMap: Map<Char, Set<UniversalLayoutSubstitution>> by lazy {
                hashMapOf(
                    'c' to sortedSetOf('\u0109'.substitution()),
                    'g' to sortedSetOf('\u011D'.substitution()),
                    'h' to sortedSetOf('\u0125'.substitution()),
                    'j' to sortedSetOf('\u0135'.substitution()),
                    's' to sortedSetOf('\u015D'.substitution()),
                    'u' to sortedSetOf('\u00EF'.substitution()),
                )
            }
        }

        private object Finnish : LanguageSubstitutionCapability() {
            override val substitutionsBySymbolsMap: Map<Char, Set<UniversalLayoutSubstitution>> by lazy {
                hashMapOf(
                    'a' to sortedSetOf('\u00E4'.substitution(), '\u00E5'.substitution()),
                    'o' to sortedSetOf('\u00F6'.substitution()),
                )
            }
        }

        private object French : LanguageSubstitutionCapability() {
            override val substitutionsBySymbolsMap: Map<Char, Set<UniversalLayoutSubstitution>> by lazy {
                hashMapOf(
                    'a' to sortedSetOf('\u00E0'.substitution(), '\u00E2'.substitution(), '\u00E6'.substitution()),
                    'c' to sortedSetOf('\u00E7'.substitution()),
                    'e' to sortedSetOf(
                        '\u00E9'.substitution(),
                        '\u00E8'.substitution(),
                        '\u00EA'.substitution(),
                        '\u00EB'.substitution()
                    ),
                    'i' to sortedSetOf('\u00EF'.substitution(), '\u00EE'.substitution()),
                    'o' to sortedSetOf('\u00F4'.substitution(), '\u0153'.substitution()),
                    'u' to sortedSetOf('\u00F9'.substitution(), '\u00FB'.substitution(), '\u00FC'.substitution()),
                    'y' to sortedSetOf('\u00FF'.substitution()),
                )
            }
        }

        private object German : LanguageSubstitutionCapability() {
            override val substitutionsBySymbolsMap: Map<Char, Set<UniversalLayoutSubstitution>> by lazy {
                hashMapOf(
                    'a' to sortedSetOf('\u00E4'.substitution()),
                    'o' to sortedSetOf('\u00F6'.substitution()),
                    's' to sortedSetOf('\u00DF'.substitution()),
                    'u' to sortedSetOf('\u00FC'.substitution()),
                )
            }
        }

        private object Hungarian : LanguageSubstitutionCapability() {
            override val substitutionsBySymbolsMap: Map<Char, Set<UniversalLayoutSubstitution>> by lazy {
                hashMapOf(
                    'a' to sortedSetOf('\u00E1'.substitution()),
                    'e' to sortedSetOf('\u00E9'.substitution()),
                    'i' to sortedSetOf('\u00ED'.substitution()),
                    'o' to sortedSetOf('\u00F6'.substitution(), '\u00F3'.substitution(), '\u0151'.substitution()),
                    'u' to sortedSetOf('\u00FC'.substitution(), '\u00FA'.substitution(), '\u0171'.substitution()),
                )
            }
        }

        private object Italian : LanguageSubstitutionCapability() {
            override val substitutionsBySymbolsMap: Map<Char, Set<UniversalLayoutSubstitution>> by lazy {
                hashMapOf(
                    'a' to sortedSetOf('\u00E0'.substitution()),
                    'e' to sortedSetOf('\u00E8'.substitution(), '\u00E9'.substitution()),
                    'i' to sortedSetOf('\u00EC'.substitution()),
                    'o' to sortedSetOf('\u00F2'.substitution(), '\u00F3'.substitution()),
                    'u' to sortedSetOf('\u00F9'.substitution()),
                )
            }
        }

        private object Icelandic : LanguageSubstitutionCapability() {
            override val substitutionsBySymbolsMap: Map<Char, Set<UniversalLayoutSubstitution>> by lazy {
                hashMapOf(
                    'a' to sortedSetOf('\u00E1'.substitution(), '\u00E6'.substitution()),
                    'd' to sortedSetOf('\u00F0'.substitution()),
                    'e' to sortedSetOf('\u00E9'.substitution()),
                    'i' to sortedSetOf('\u00ED'.substitution()),
                    'o' to sortedSetOf('\u00F3'.substitution(), '\u00F6'.substitution()),
                    't' to sortedSetOf('\u00FE'.substitution()),
                    'u' to sortedSetOf('\u00FA'.substitution()),
                    'y' to sortedSetOf('\u00FD'.substitution()),
                )
            }
        }

        private object Maori : LanguageSubstitutionCapability() {
            override val substitutionsBySymbolsMap: Map<Char, Set<UniversalLayoutSubstitution>> by lazy {
                hashMapOf(
                    'a' to sortedSetOf('\u0101'.substitution()),
                    'e' to sortedSetOf('\u0113'.substitution()),
                    'i' to sortedSetOf('\u012B'.substitution()),
                    'o' to sortedSetOf('\u014D'.substitution()),
                    'u' to sortedSetOf('\u016B'.substitution()),
                )
            }
        }

        private object Norwegian : LanguageSubstitutionCapability() {
            override val substitutionsBySymbolsMap: Map<Char, Set<UniversalLayoutSubstitution>> by lazy {
                hashMapOf(
                    'a' to sortedSetOf('\u00E5'.substitution(), '\u00E6'.substitution()),
                    'o' to sortedSetOf('\u00F8'.substitution()),
                )
            }
        }

        private object Polish : LanguageSubstitutionCapability() {
            override val substitutionsBySymbolsMap: Map<Char, Set<UniversalLayoutSubstitution>> by lazy {
                hashMapOf(
                    'a' to sortedSetOf('\u0105'.substitution()),
                    'c' to sortedSetOf('\u0107'.substitution()),
                    'e' to sortedSetOf('\u0119'.substitution()),
                    'l' to sortedSetOf('\u0142'.substitution()),
                    'n' to sortedSetOf('\u0144'.substitution()),
                    'o' to sortedSetOf('\u00F3'.substitution()),
                    's' to sortedSetOf('\u015B'.substitution()),
                    'z' to sortedSetOf('\u017A'.substitution(), '\u017C'.substitution()),
                )
            }
        }

        private object Portuguese : LanguageSubstitutionCapability() {
            override val substitutionsBySymbolsMap: Map<Char, Set<UniversalLayoutSubstitution>> by lazy {
                hashMapOf(
                    'a' to sortedSetOf(
                        '\u00E3'.substitution(),
                        '\u00E1'.substitution(),
                        '\u00E0'.substitution(),
                        '\u00E2'.substitution()
                    ),
                    'c' to sortedSetOf('\u00E7'.substitution()),
                    'e' to sortedSetOf('\u00E9'.substitution(), '\u00EA'.substitution()),
                    'i' to sortedSetOf('\u00ED'.substitution()),
                    'o' to sortedSetOf('\u00F5'.substitution(), '\u00F3'.substitution(), '\u00F4'.substitution()),
                    'u' to sortedSetOf('\u00FA'.substitution(), '\u00FC'.substitution()),
                )
            }
        }

        private object Romanian : LanguageSubstitutionCapability() {
            override val substitutionsBySymbolsMap: Map<Char, Set<UniversalLayoutSubstitution>> by lazy {
                hashMapOf(
                    'a' to sortedSetOf('\u0103'.substitution(), '\u00E2'.substitution()),
                    'i' to sortedSetOf('\u00EE'.substitution()),
                    's' to sortedSetOf('\u015F'.substitution()),
                    't' to sortedSetOf('\u0163'.substitution()),
                )
            }
        }

        private object Spanish : LanguageSubstitutionCapability() {
            override val substitutionsBySymbolsMap: Map<Char, Set<UniversalLayoutSubstitution>> by lazy {
                hashMapOf(
                    'a' to sortedSetOf('\u00E1'.substitution()),
                    'e' to sortedSetOf('\u00E9'.substitution()),
                    'i' to sortedSetOf('\u00ED'.substitution()),
                    'n' to sortedSetOf('\u00F1'.substitution()),
                    'o' to sortedSetOf('\u00F3'.substitution()),
                    'u' to sortedSetOf('\u00FA'.substitution(), '\u00FC'.substitution()),
                    '?' to sortedSetOf('\u00BF'.substitution()),
                    '!' to sortedSetOf('\u00A1'.substitution()),
                )
            }
        }

        private object Swedish : LanguageSubstitutionCapability() {
            override val substitutionsBySymbolsMap: Map<Char, Set<UniversalLayoutSubstitution>> by lazy {
                hashMapOf(
                    'a' to sortedSetOf('\u00E4'.substitution(), '\u00E5'.substitution()),
                    'o' to sortedSetOf('\u00F6'.substitution()),
                    's' to sortedSetOf('\u0161'.substitution()),
                    'z' to sortedSetOf('\u017E'.substitution()),
                )
            }
        }
    }
}