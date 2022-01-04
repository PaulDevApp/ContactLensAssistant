package com.appsforlife.contactlensmanagement.domain.usecases.itemusecases

import com.appsforlife.contactlensmanagement.domain.entities.LensItem
import com.appsforlife.contactlensmanagement.domain.repositories.LensItemRepository

class AddLensItemUseCase(private val lensItemRepository: LensItemRepository) {

    suspend fun addLensItem(lensItem: LensItem) {
        lensItemRepository.addLensItem(lensItem)
    }
}