package com.appsforlife.contactlensmanagement.domain.usecases

import com.appsforlife.contactlensmanagement.domain.repository.LensItemRepository

class RemoveAllItemsUseCase(private val lensItemRepository: LensItemRepository) {

    suspend fun removeAllCounts() {
        lensItemRepository.removeAllItems()
    }
}