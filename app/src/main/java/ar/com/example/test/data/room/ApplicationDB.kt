package ar.com.example.test.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import ar.com.example.test.data.models.Person

@Database(entities = [Person::class], version = 1)
abstract class ApplicationDB : RoomDatabase(){

    abstract fun personDao():PersonDao
}