package ar.com.example.test.domain

import ar.com.example.test.data.models.Person
import ar.com.example.test.data.room.PersonDao
import javax.inject.Inject

class PersonRepoImpl @Inject constructor(private val dao: PersonDao): PersonRepo {
    override suspend fun savePerson(person: Person) {
        dao.savePerson(person)
    }

    override suspend fun getAllPersons(): List<Person> = dao.getAllPersons()
}