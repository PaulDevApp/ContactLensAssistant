package com.appsforlife.contactlensmanagement.domain.usecases.itemusecases

import com.appsforlife.contactlensmanagement.domain.repositories.LensItemRepository

class RemoveAllItemsUseCase(private val lensItemRepository: LensItemRepository) {

    suspend fun removeAllCounts() {
        lensItemRepository.removeAllItems()
    }
}