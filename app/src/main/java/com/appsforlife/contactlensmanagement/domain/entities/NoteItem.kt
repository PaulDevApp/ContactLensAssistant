package com.appsforlife.contactlensmanagement.domain.entities

data class NoteItem(
    val leftOpticalPower: String,
    val leftRadiusOfCurvature: String,
    val leftCylinderPower: String,
    val leftAxis: String,
    val rightOpticalPower: String,
    val rightRadiusOfCurvature: String,
    val rightCylinderPower: String,
    val rightAxis: String,
    val title: String,
    val text: String,
    val id: Int = UNDEFINED_ID
) {
    companion object {
        const val UNDEFINED_ID = 0
    }
}