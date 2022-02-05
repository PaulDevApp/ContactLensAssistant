package com.appsforlife.contactlensmanagement.data.noteitemdata

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note_item_db_name")
data class NoteItemDbModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val leftOpticalPower: String,
    val leftRadiusOfCurvature: String,
    val leftCylinderPower: String,
    val leftAxis: String,
    val rightOpticalPower: String,
    val rightRadiusOfCurvature: String,
    val rightCylinderPower: String,
    val rightAxis: String,
    val title: String,
    val text: String
)
