package ar.com.example.test.presentation

import androidx.lifecycle.*
import ar.com.example.test.data.models.Person
import ar.com.example.test.domain.PersonRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

const val DNI_NORMAL_LENGTH = 8

@HiltViewModel
class PersonViewModel @Inject constructor(private val repo: PersonRepo) : ViewModel() {


    private val _dataBaseIsFull = MutableLiveData<Boolean>()
    val dataBaseIsFull: LiveData<Boolean>
        get() = _dataBaseIsFull

    fun checkDB(person: Person){
        viewModelScope.launch(Dispatchers.Main) {
            if (repo.getCountEntries() == 5){
                _dataBaseIsFull.value = true
            }else {
                _dataBaseIsFull.value = false
                savePerson(person)
            }
        }
    }

    private fun savePerson(person: Person) {
    viewModelScope.launch(Dispatchers.IO) {
        repo.savePerson(person)
        }
    }

    fun getAllPersons() = liveData(viewModelScope.coroutineContext + Dispatchers.IO){
        emit(repo.getAllPersons())
    }

    private val _fieldsAreValid = MutableLiveData<Boolean>()
    val fieldsAreValid: LiveData<Boolean>
        get() =
            _fieldsAreValid

    private val _ageIsValid = MutableLiveData<Boolean>()
    val ageIsValid: LiveData<Boolean>
        get() = _ageIsValid

    fun validateFields(name: String, lastName: String, age: String, dni: String) {
        if (age.isNotEmpty()){
            _ageIsValid.value = age.toIntOrNull() in 21..50
        }
        _fieldsAreValid.value = name.isNotEmpty() && lastName.isNotEmpty() && _ageIsValid.value == true && dni.isNotEmpty() && dni.length == DNI_NORMAL_LENGTH
    }

}