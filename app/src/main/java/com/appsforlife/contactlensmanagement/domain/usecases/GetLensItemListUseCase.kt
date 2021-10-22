package com.appsforlife.contactlensmanagement.domain.usecases

import androidx.lifecycle.LiveData
import com.appsforlife.contactlensmanagement.domain.entity.LensItem
import com.appsforlife.contactlensmanagement.domain.repository.LensItemRepository

class GetLensItemListUseCase(private val lensItemRepository: LensItemRepository) {

    fun getLensList(): LiveData<List<LensItem>> {
        return lensItemRepository.getLensItemList()
    }
}