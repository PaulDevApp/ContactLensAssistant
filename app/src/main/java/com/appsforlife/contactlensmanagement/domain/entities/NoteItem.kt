package com.appsforlife.contactlensmanagement.domain.entities

data class NoteItem(
    val leftOpticalPower: String,
    val leftRadiusOfCurvature: String,
    val leftDiameter: String,
    val rightOpticalPower: String,
    val rightRadiusOfCurvature: String,
    val rightDiameter: String,
    val title: String,
    val id: Int = UNDEFINED_ID
) {
    companion object {
        const val UNDEFINED_ID = 0
    }
}