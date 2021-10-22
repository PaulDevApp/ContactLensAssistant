package com.appsforlife.contactlensmanagement.domain.usecases

import com.appsforlife.contactlensmanagement.domain.entity.LensItem
import com.appsforlife.contactlensmanagement.domain.repository.LensItemRepository

class DeleteLensItemUseCase(private val lensItemRepository: LensItemRepository) {

    suspend fun deleteLensItem(lensItem: LensItem) {
        lensItemRepository.deleteLensItem(lensItem)
    }
}