package ar.com.example.test.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Person(
    @PrimaryKey(autoGenerate = false)
    val dni: Int,
    val name: String,
    val lastName: String,
    val age: Int
)
