package ru.skillbranch.devintensive.models

class Bender(var status:Status = Status.NORMAL, var question: Question = Question.NAME) {

    fun askQuestion():String = when (question){
        Question.NAME -> Question.NAME.question
        Question.PROFESSION -> Question.PROFESSION.question
        Question.MATERIAL -> Question.MATERIAL.question
        Question.BDAY -> Question.BDAY.question
        Question.SERIAL -> Question.SERIAL.question
        Question.IDLE -> Question.IDLE.question
    }

    fun listenAnswer(answer:String):Pair<String,Triple<Int,Int,Int>> {
        return when {
            question.isValid(answer).isNotEmpty() -> {
                question.isValid(answer) to status.color
            }
            question.answer.contains(answer.toLowerCase()) -> {
                question = question.nextQuestion()
                "Отлично - ты справился\n${question.question}" to status.color
            }
            status == Status.CRITICAL -> {
                question = Question.NAME
                status = Status.NORMAL
                "Это неправильный ответ. Давай все по новой\n${question.question}" to status.color
            }
            else -> {
                status = status.nextStatus()
                "Это неправильный ответ\n${question.question}" to status.color
            }
        }
    }

    enum class Status(val color:Triple<Int,Int,Int>){
        NORMAL(Triple(255,255,255)),
        WARNING(Triple(255,120,0)),
        DANGER(Triple(255,60,60)),
        CRITICAL(Triple(255,0,0));
        fun nextStatus():Status{
            return if (this.ordinal < values().lastIndex){
                values()[this.ordinal + 1]
            }else{
                values()[0]
            }
        }
    }

    enum class Question(val question: String, val answer: List<String>){
        NAME("Как меня зовут?", listOf("бендер", "bender")) {
            override fun nextQuestion(): Question = PROFESSION
            override fun isValid(answer:String): String =
                if (Regex("^[A-ZА-Я][a-zA-Zа-яА-Я]*").matches(answer)) "" else "Имя должно начинаться с заглавной буквы"
        },
        PROFESSION("Назови мою профессию?", listOf("сгибальщик", "bender")) {
            override fun nextQuestion(): Question = MATERIAL
            override fun isValid(answer:String): String =
                 if (Regex("^[a-zа-я][a-zA-Zа-яА-Я]*").matches(answer)) "" else "Профессия должна начинаться со строчной буквы"
        },
        MATERIAL("Из чего я сделан?", listOf("металл", "дерево", "metal", "iron", "wood")) {
            override fun nextQuestion(): Question = BDAY
            override fun isValid(answer:String): String =
                if (Regex("/d*").matches(answer)) "Материал не должен содержать цифр" else ""
        },
        BDAY("Когда меня создали?", listOf("2993")) {
            override fun nextQuestion(): Question = SERIAL
            override fun isValid(answer:String): String =
                 if (Regex("/D*").matches(answer)) "Год моего рождения должен содержать только цифры" else ""
        },
        SERIAL("Мой серийный номер?", listOf("2716057")) {
            override fun nextQuestion(): Question = IDLE
            override fun isValid(answer:String): String =
                 if (Regex("/d{7}").matches(answer)) "Серийный номер содержит только цифры, и их 7" else ""
        },
        IDLE("На этом все, вопросов больше нет", listOf()) {
            override fun nextQuestion(): Question = IDLE
            override fun isValid(answer:String): String = ""
        };

        abstract fun nextQuestion():Question
        abstract fun isValid(answer:String):String
    }
}






