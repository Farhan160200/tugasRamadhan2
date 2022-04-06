package com.example.tugasramadhanday2and3

import androidx.lifecycle.*
import kotlinx.coroutines.launch

class NameViewModel(private val repository: NameRepository) : ViewModel() {

    val allNames: LiveData<List<Name>> = repository.allNames.asLiveData()

    fun insert(name: Name) = viewModelScope.launch {
        repository.insert(name)
    }
}

class WordViewModelFactory(private val repository: NameRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NameViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return NameViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}