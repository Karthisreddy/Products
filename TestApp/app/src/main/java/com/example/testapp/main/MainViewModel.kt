package com.example.testapp.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testapp.data.DummyResponse
import com.example.testapp.data.repository.MainRepository
import com.example.testapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: MainRepository
) : ViewModel() {

    sealed class Event {
        class Success(val resultData: DummyResponse?) : Event()
        class Failure(val errorText: String?) : Event()
        object Loading : Event()
        object Empty : Event()
    }

    private val _dummyData = MutableStateFlow<Event>(Event.Empty)
    val dummyData: StateFlow<Event> = _dummyData

    init {
        getData()
    }

    private fun getData() {
        viewModelScope.launch(Dispatchers.IO) {
            _dummyData.value = Event.Loading
            when (val response = repository.getData()) {
                is Resource.Error -> {
                    _dummyData.value = Event.Failure(response.message)
                }

                is Resource.Success -> {
                    val data = response.data
                    _dummyData.value = Event.Success(data)
                }
            }
        }
    }
}