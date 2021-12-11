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


    fun savePerson(person: Person){
        viewModelScope.launch(Dispatchers.IO) {
            repo.savePerson(person)
        }
    }

    fun getAllPersons() = liveData(viewModelScope.coroutineContext + Dispatchers.IO){
        emit(repo.getAllPersons())
    }

    private val _enableButtons = MutableLiveData(false)
    val enableButtons: LiveData<Boolean>
        get() =
            _enableButtons

    fun validateFields(name: String, lastName: String, age: String, dni: String) {

        _enableButtons.value = name.isNotEmpty() && lastName.isNotEmpty() && age.toIntOrNull() in 21..60 && dni.isNotEmpty() && dni.length == DNI_NORMAL_LENGTH
    }

}