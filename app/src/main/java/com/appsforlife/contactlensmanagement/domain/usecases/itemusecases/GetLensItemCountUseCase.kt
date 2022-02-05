package com.appsforlife.contactlensmanagement.domain.usecases.itemusecases

import androidx.lifecycle.LiveData
import com.appsforlife.contactlensmanagement.domain.repositories.LensItemRepository
import javax.inject.Inject

class GetLensItemCountUseCase @Inject constructor(
    private val lensItemRepository: LensItemRepository
) {

    fun getLensItemCount(): LiveData<Int> {
        return lensItemRepository.getLensItemCount()
    }
}