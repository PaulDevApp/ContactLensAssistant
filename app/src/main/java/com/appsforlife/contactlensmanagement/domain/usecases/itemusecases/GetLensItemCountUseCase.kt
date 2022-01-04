package com.appsforlife.contactlensmanagement.domain.usecases.itemusecases

import androidx.lifecycle.LiveData
import com.appsforlife.contactlensmanagement.domain.repositories.LensItemRepository

class GetLensItemCountUseCase(private val lensItemRepository: LensItemRepository) {

    fun getLensItemCount(): LiveData<Int> {
        return lensItemRepository.getLensItemCount()
    }
}