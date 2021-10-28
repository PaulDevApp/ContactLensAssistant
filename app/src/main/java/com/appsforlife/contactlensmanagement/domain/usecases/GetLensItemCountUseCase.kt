package com.appsforlife.contactlensmanagement.domain.usecases

import androidx.lifecycle.LiveData
import com.appsforlife.contactlensmanagement.domain.repository.LensItemRepository

class GetLensItemCountUseCase(private val lensItemRepository: LensItemRepository) {

    fun getLensItemCount(): LiveData<Int> {
        return lensItemRepository.getLensItemCount()
    }
}