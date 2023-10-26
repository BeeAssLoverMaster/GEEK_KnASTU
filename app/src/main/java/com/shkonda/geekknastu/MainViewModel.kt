package com.shkonda.geekknastu

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shkonda.geekknastu.data_base.events.MainDb
import com.shkonda.geekknastu.util.ListItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    val mainDB: MainDb
) : ViewModel() {
    val mainList = mutableStateOf(emptyList<ListItem>())
    private var job: Job? = null

    fun getAllItemsByCategory(cat: String) {
        job?.cancel()
        job = viewModelScope.launch {
            mainDB.dao.getAllItemsByCategory(cat).collect { list ->
                mainList.value = list
            }
        }

    }
    fun getFavorites() = viewModelScope.launch {
        job?.cancel()
        job = viewModelScope.launch {
            mainDB.dao.getFavorites().collect { list ->
                mainList.value = list
            }
        }
    }

    fun insertItem(item: ListItem) = viewModelScope.launch {
        mainDB.dao.insertItem(item)
    }
    fun deleteItem(item: ListItem) = viewModelScope.launch {
        mainDB.dao.deleteItem(item)
    }

}