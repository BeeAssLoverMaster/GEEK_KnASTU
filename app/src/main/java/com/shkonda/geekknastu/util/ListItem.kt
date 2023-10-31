package com.shkonda.geekknastu.util

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "events")
data class ListItem(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val title: String,
    val imageName: String,
    val category: String,
    val isFav: Boolean,
    val dateStart: String,
    val dateEnd: String
)
