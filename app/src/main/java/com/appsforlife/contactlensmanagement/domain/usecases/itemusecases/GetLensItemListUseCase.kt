package com.appsforlife.contactlensmanagement.domain.usecases.itemusecases

import androidx.lifecycle.LiveData
import com.appsforlife.contactlensmanagement.domain.entities.LensItem
import com.appsforlife.contactlensmanagement.domain.repositories.LensItemRepository
import javax.inject.Inject

class GetLensItemListUseCase @Inject constructor(
    private val lensItemRepository: LensItemRepository
) {

    fun getLensList(): LiveData<List<LensItem>> {
        return lensItemRepository.getLensItemList()
    }
}