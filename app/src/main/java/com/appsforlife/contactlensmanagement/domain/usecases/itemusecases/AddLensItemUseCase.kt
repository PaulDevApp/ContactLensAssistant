package com.appsforlife.contactlensmanagement.domain.usecases.itemusecases

import com.appsforlife.contactlensmanagement.domain.entities.LensItem
import com.appsforlife.contactlensmanagement.domain.repositories.LensItemRepository
import javax.inject.Inject

class AddLensItemUseCase @Inject constructor(
    private val lensItemRepository: LensItemRepository
) {

    suspend fun addLensItem(lensItem: LensItem) {
        lensItemRepository.addLensItem(lensItem)
    }
}