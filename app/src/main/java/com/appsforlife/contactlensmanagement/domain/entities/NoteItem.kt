package com.appsforlife.contactlensmanagement.domain.entities

data class NoteItem(
    val opticalPower: Long,
    val radiusOfCurvature: Long,
    val diameter: Long,
    val id: Int = UNDEFINED_ID
) {
    companion object {
        const val UNDEFINED_ID = 0
    }
}