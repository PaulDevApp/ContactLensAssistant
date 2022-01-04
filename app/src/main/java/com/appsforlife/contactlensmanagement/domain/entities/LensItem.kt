package com.appsforlife.contactlensmanagement.domain.entities

data class LensItem(
    val date: String,
    var id: Int = UNDEFINED_ID,
) {
    companion object {
        const val UNDEFINED_ID = 0
    }
}
