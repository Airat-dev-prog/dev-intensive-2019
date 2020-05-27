package ru.skillbranch.devintensive.extensions

import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.abs

const val SECOND = 1000L
const val MINUTE = 60 * SECOND
const val HOUR = 60 * MINUTE
const val DAY = 24 * HOUR

fun Date.format(pattern: String="HH:mm:ss dd.MM.yy"):String{
    val dateFormat = SimpleDateFormat(pattern, Locale("ru"))
    return dateFormat.format(this)
}

fun Date.add(value: Int, units: TimeUnits = TimeUnits.SECOND): Date{
    var time = this.time

    time += when(units){
        TimeUnits.SECOND -> value * SECOND
        TimeUnits.MINUTE -> value * MINUTE
        TimeUnits.HOUR -> value * HOUR
        TimeUnits.DAY -> value * DAY
        else -> throw  IllegalStateException("invalid unit")
    }

    this.time = time
    return this
}

private fun getTimeUnit( value:Long, units:TimeUnits):String{
    return abs(value).toString() + " " + when(units){
        TimeUnits.SECOND -> when(abs(value)){
            in 5L..20L -> "секунд"
            else -> when(abs(value).rem(10)){
                0L -> "секунд"
                1L -> "секунду"
                in 2L..4L -> "секунды"
                in 5L..9L -> "секунд"
                else -> ""
            }
        }
        TimeUnits.MINUTE -> when(abs(value)){
            in 5L..20L -> "минут"
            else -> when(abs(value).rem(10)){
                0L -> "минут"
                1L -> "минуту"
                in 2L..4L -> "минуты"
                in 5L..9L -> "минут"
                else -> ""
            }
        }
        TimeUnits.HOUR -> when(abs(value)){
            in 5L..20L -> "часов"
            else -> when(abs(value).rem(10)){
                0L -> "часов"
                1L -> "час"
                in 2L..4L -> "часа"
                in 5L..9L -> "часов"
                else -> ""
            }
        }
        TimeUnits.DAY -> when(abs(value)){
            in 5L..20L -> "дней"
            else -> when(abs(value).rem(10)){
                0L -> "дней"
                1L -> "день"
                in 2L..4L -> "дня"
                in 5L..9L -> "дней"
                else -> ""
            }
        }
        else -> ""
    }
}

fun Date.humanizeDiff(date:Date = Date()): String {
    val milliseconds = (this.time - date.time)//1000L*1000L
    val seconds : Long = milliseconds/1000L
    val minutes : Long = seconds/60L
    val hours : Long = minutes/60L
    val days : Long = hours/24L

    //println("$seconds с -> $minutes м -> $hours ч -> $days д")
    return when (abs(milliseconds)) {
        in 0 * SECOND .. 1L * SECOND -> "только что"
        in 1L * SECOND .. 45L * SECOND -> if (milliseconds < 0) "несколько секунд назад" else "через несколько секунд"
        in 45L * SECOND .. 75L * SECOND -> if (milliseconds < 0) "минуту назад" else "через минуту"
        in 75L * SECOND .. 45L * MINUTE -> if (milliseconds < 0) "${getTimeUnit(milliseconds/MINUTE, TimeUnits.MINUTE)} назад" else "через ${getTimeUnit(milliseconds/MINUTE, TimeUnits.MINUTE)}"
        in 45L * MINUTE .. 75L * MINUTE -> if (milliseconds < 0) "час назад" else "через час"
        in 75L * MINUTE .. 22L * HOUR -> if (milliseconds < 0) "${getTimeUnit(milliseconds/HOUR, TimeUnits.HOUR)} назад" else "через ${getTimeUnit(milliseconds/HOUR, TimeUnits.HOUR)}"
        in 22L * HOUR .. 26L * HOUR -> if (milliseconds < 0) "день назад" else "через день"
        in 26L * HOUR .. 360L * DAY -> if (milliseconds < 0) "${getTimeUnit(milliseconds/DAY,TimeUnits.DAY)} назад" else "через ${getTimeUnit(milliseconds/DAY,TimeUnits.DAY)}"
        else -> {if (milliseconds < 0) "более года назад" else "более чем через год"}
    }
}

enum class TimeUnits{
    SECOND{
        override fun plural(value : Int): String {
            return getTimeUnit(value.toLong(),this)
        }
    },
    MINUTE{
        override fun plural(value : Int): String {
            return getTimeUnit(value.toLong(),this)
        }
    },
    HOUR{
        override fun plural(value : Int): String {
            return getTimeUnit(value.toLong(),this)
        }
    },
    DAY{
        override fun plural(value : Int): String {
            return getTimeUnit(value.toLong(),this)
        }
    };
    abstract fun plural(value : Int): String
}