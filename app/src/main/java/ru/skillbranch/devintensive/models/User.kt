package ru.skillbranch.devintensive.models

import ru.skillbranch.devintensive.utils.Utils
import java.util.*

data class User(
    val id : String,
    var firstName : String?,
    var lastName : String?,
    var avatar : String?,
    var rating : Int = 0,
    var respect : Int = 0,
    var lastVisit : Date? = Date(),
    var isOnline : Boolean = false

){
    //var introBit: String

    constructor(id: String, firstName: String?, lastName: String?) : this(
        id = id,
        firstName = firstName,
        lastName = lastName,
        avatar = null
    )

    constructor(id: String) : this(id, "John", "Doe")

    init {
        //introBit = getIntro()

        println("It's Alive!!! \n" +
                "${if (lastName==="Doe") "His name is $firstName $lastName" else "And his name is $firstName $lastName!!!" }\n" + ""
                //"${getIntro()}"
        )
    }

/*
    private fun getIntro()="""
        tu tu ru tuuuuuuu !!!
        tu tu ru tuuuuuuu ...
        
        tu tu ru tuuuuuuu !!!
        tu tu ru tuuuuuuu ...
        ${"\n\n\n"}
        $firstName $lastName
    """.trimIndent()

    fun printMe()=
        println("""
            id: $id
            firstName: $firstName
            lastName: $lastName
            avatar: $avatar
            rating: $rating
            respect: $respect
            lastVisit: $lastVisit
            isOnline: $isOnline
        """.trimIndent())
*/

    companion object Factory {
        private var lastID : Int = -1
        fun makeUser(fullName:String?) : User{
            lastID++
/*
            val parts : List<String>? = fullName?.split(" ")
            val firstName = parts?.getOrNull(0)
            val lastName = parts?.getOrNull(1)
*/
            val (firstName, lastName) = Utils.parseFullName(fullName)
            return User(id = "$lastID", firstName = firstName, lastName = lastName )
        }
    }

    class Builder() {
        private var id: String = ""
        private var firstName: String = ""
        private var lastName: String = ""
        private var avatar: String = ""
        private var rating: Int = 0
        private var respect: Int = 0
        private var lastVisit: Date? = null
        private var isOnline: Boolean = false

        fun id( value : String)  : Builder{
            this.id = value
            return this
        }

        fun firstName( value : String)  : Builder{
            this.firstName = value
            return this
        }

        fun lastName( value : String)  : Builder{
            this.lastName = value
            return this
        }

        fun avatar( value : String)  : Builder{
            this.avatar = value
            return this
        }

        fun rating( value : Int)  : Builder{
            this.rating = value
            return this
        }

        fun respect( value : Int)  : Builder{
            this.respect = value
            return this
        }

        fun lastVisit( value : Date)  : Builder{
            this.lastVisit = value
            return this
        }

        fun isOnline( value : Boolean)  : Builder{
            this.isOnline = value
            return this
        }

        fun build(): User {
            return User(
                this.id,
                this.firstName,
                this.lastName,
                this.avatar,
                this.rating,
                this.respect,
                this.lastVisit,
                this.isOnline)
        }
    }
}
