package ru.skillbranch.devintensive.utils

import android.service.voice.AlwaysOnHotwordDetector
import kotlin.math.min

object Utils {
    fun parseFullName(fullName:String?):Pair<String?,String?>{
        val modifyFullName:String? = fullName?.replace(Regex(" {1,}")," ")?.trim()
        val parts : List<String>? = if (modifyFullName == "") null else modifyFullName?.split(" ")

        val firstName = parts?.getOrNull(0)
        val lastName = parts?.getOrNull(1)
        return firstName to  lastName
    }

    fun transliteration(payload: String, divider: String = " "): String {
        var transliterationString : String = ""
        payload.replace(" ", divider).forEach {
            when(it){
                'A' -> transliterationString +=  "A"
                'Б' -> transliterationString +=  "B"
                'В' -> transliterationString +=  "V"
                'Г' -> transliterationString +=  "G"
                'Д' -> transliterationString +=  "D"
                'Е' -> transliterationString +=  "E"
                'Ё' -> transliterationString +=  "E"
                'Ж' -> transliterationString +=  "Zh"
                'З' -> transliterationString +=  "Z"
                'И' -> transliterationString +=  "I"
                'Й' -> transliterationString +=  "I"
                'К' -> transliterationString +=  "K"
                'Л' -> transliterationString +=  "L"
                'М' -> transliterationString +=  "M"
                'Н' -> transliterationString +=  "N"
                'О' -> transliterationString +=  "O"
                'П' -> transliterationString +=  "P"
                'Р' -> transliterationString +=  "R"
                'С' -> transliterationString +=  "S"
                'Т' -> transliterationString +=  "T"
                'У' -> transliterationString +=  "U"
                'Ф' -> transliterationString +=  "F"
                'Х' -> transliterationString +=  "H"
                'Ц' -> transliterationString +=  "C"
                'Ч' -> transliterationString +=  "Ch"
                'Ш' -> transliterationString +=  "Sh"
                'Щ' -> transliterationString +=  "Sh'"
                'Ъ' -> transliterationString +=  ""
                'Ы' -> transliterationString +=  "I"
                'Ь' -> transliterationString +=  ""
                'Э' -> transliterationString +=  "E"
                'Ю' -> transliterationString +=  "Yu"
                'Я' -> transliterationString +=  "Ya"
                'а' -> transliterationString +=  "a"
                'б' -> transliterationString +=  "b"
                'в' -> transliterationString +=  "v"
                'г' -> transliterationString +=  "g"
                'д' -> transliterationString +=  "d"
                'е' -> transliterationString +=  "e"
                'ё' -> transliterationString +=  "e"
                'ж' -> transliterationString +=  "zh"
                'з' -> transliterationString +=  "z"
                'и' -> transliterationString +=  "i"
                'й' -> transliterationString +=  "i"
                'к' -> transliterationString +=  "k"
                'л' -> transliterationString +=  "l"
                'м' -> transliterationString +=  "m"
                'н' -> transliterationString +=  "n"
                'о' -> transliterationString +=  "o"
                'п' -> transliterationString +=  "p"
                'р' -> transliterationString +=  "r"
                'с' -> transliterationString +=  "s"
                'т' -> transliterationString +=  "t"
                'у' -> transliterationString +=  "u"
                'ф' -> transliterationString +=  "f"
                'х' -> transliterationString +=  "h"
                'ц' -> transliterationString +=  "c"
                'ч' -> transliterationString +=  "ch"
                'ш' -> transliterationString +=  "sh"
                'щ' -> transliterationString +=  "sh'"
                'ъ' -> transliterationString +=  ""
                'ы' -> transliterationString +=  "i"
                'ь' -> transliterationString +=  ""
                'э' -> transliterationString +=  "e"
                'ю' -> transliterationString +=  "yu"
                'я' -> transliterationString +=  "ya"
                else -> transliterationString += it.toString()
            }
        }
        return transliterationString
    }

    fun toInitials(firstName: String?, lastName: String?): String? {
        val initials:String = (firstName.orEmpty()+" ").substring(0,1).trim().toUpperCase() + (lastName.orEmpty()+" ").substring(0,1).trim().toUpperCase()
        return if (initials == "") null else initials
    }
}