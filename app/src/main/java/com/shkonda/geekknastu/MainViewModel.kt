package com.shkonda.geekknastu

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shkonda.geekknastu.data_base.events.MainDb
import com.shkonda.geekknastu.util.ListItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    val mainDB: MainDb
) : ViewModel() {
    val mainList = mutableStateOf(emptyList<ListItem>())

    fun getAllItemsByCategory(cat: String) = viewModelScope.launch {
        mainList.value = mainDB.dao.getAllItemsByCategory(cat)
    }

    fun insertItem(item: ListItem) = viewModelScope.launch {
        mainDB.dao.insertItem(item)
    }
    fun deleteItem(item: ListItem) = viewModelScope.launch {
        mainDB.dao.deleteItem(item)
    }

}