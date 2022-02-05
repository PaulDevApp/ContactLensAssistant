package com.appsforlife.contactlensmanagement.domain.usecases.itemusecases

import com.appsforlife.contactlensmanagement.domain.entities.LensItem
import com.appsforlife.contactlensmanagement.domain.repositories.LensItemRepository
import javax.inject.Inject

class DeleteLensItemUseCase @Inject constructor(
    private val lensItemRepository: LensItemRepository
) {

    suspend fun deleteLensItem(lensItem: LensItem) {
        lensItemRepository.deleteLensItem(lensItem)
    }
}