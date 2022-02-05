package com.appsforlife.contactlensmanagement.domain.usecases.itemusecases

import com.appsforlife.contactlensmanagement.domain.repositories.LensItemRepository
import javax.inject.Inject

class RemoveAllItemsUseCase @Inject constructor(
    private val lensItemRepository: LensItemRepository
) {
    suspend fun removeAllCounts() {
        lensItemRepository.removeAllItems()
    }
}