package ru.skillbranch.devintensive.extensions

fun String.truncate(len : Int=16):String{
    val truncateString : String = this.substring(0, len).trim()
    return truncateString + "${if(truncateString != this.trim()) "..." else ""}"
}
 fun String.stripHtml():String{
     return this.replace(Regex(" {1,}")," ")?.trim()
         .replace(Regex("<\\/?[A-Za-z]+[^>]*>"),"")
 }
/*
Необходимо реализовать метод stripHtml для очистки строки от лишних пробелов, html тегов, escape последовательностей

Реализуй extension позволяющий очистить строку от html тегов и html escape последовательностей ("& < > ' ""),
а так же удалить пустые символы (пробелы) между словами если их больше 1. Необходимо вернуть модифицированную строку
Пример:
"<p class="title">Образовательное IT-сообщество Skill Branch</p>".stripHtml() //Образовательное IT-сообщество Skill Branch
"<p>Образовательное       IT-сообщество Skill Branch</p>".stripHtml() //Образовательное IT-сообщество Skill Branch
*/