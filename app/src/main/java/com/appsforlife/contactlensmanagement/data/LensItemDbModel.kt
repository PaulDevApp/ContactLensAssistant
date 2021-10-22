package com.appsforlife.contactlensmanagement.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "lens_item_db_name")
data class LensItemDbModel(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val date:String
)
