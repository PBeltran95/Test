package ar.com.example.test.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ar.com.example.test.data.models.Person

@Dao
interface PersonDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun savePerson(person: Person)

    @Query("SELECT * FROM person")
    suspend fun getAllPersons(): List<Person>

}