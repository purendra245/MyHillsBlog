package com.example.myhillsblog.ui

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.example.myhillsblog.model.Blogs
import com.example.myhillsblog.repository.MainRepository
import com.example.myhillsblog.utils.DataState
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class MainViewModel
@ViewModelInject
constructor(
    @Assisted private val savedStateHandle: SavedStateHandle,
    private val repository: MainRepository

): ViewModel()

{

    private val _dataState : MutableLiveData<DataState<List<Blogs>>> = MutableLiveData()
    val dataState : LiveData<DataState<List<Blogs>>>
        get() = _dataState

    fun setStateEvent(mainStateEvent: MainStateEvent){
        viewModelScope.launch {
            when(mainStateEvent){
                is MainStateEvent.GetBlogEvent->{

                    repository.getBlog().onEach {
                        dataState ->
                        _dataState.value = dataState
                    }.launchIn(viewModelScope)

                }
                is MainStateEvent.None->{
                    // Will do later
                }
            }
        }
    }


}


sealed class MainStateEvent{

    object GetBlogEvent:MainStateEvent()
    object None : MainStateEvent()
}