package com.appsforlife.contactlensmanagement.domain.entity

data class LensItem(
    val date: String,
    var id: Int = UNDEFINED_ID,
) {
    companion object {
        const val UNDEFINED_ID = 0
    }
}
