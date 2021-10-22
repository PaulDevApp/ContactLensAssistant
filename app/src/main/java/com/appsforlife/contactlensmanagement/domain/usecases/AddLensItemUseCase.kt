package com.appsforlife.contactlensmanagement.domain.usecases

import com.appsforlife.contactlensmanagement.domain.entity.LensItem
import com.appsforlife.contactlensmanagement.domain.repository.LensItemRepository

class AddLensItemUseCase(private val lensItemRepository: LensItemRepository) {

    suspend fun addLensItem(lensItem: LensItem) {
        lensItemRepository.addLensItem(lensItem)
    }
}