package ar.com.example.test.domain

import ar.com.example.test.data.models.Person

interface PersonRepo {

    suspend fun savePerson(person: Person)

    suspend fun getAllPersons():List<Person>

    suspend fun getCountEntries():Int

}