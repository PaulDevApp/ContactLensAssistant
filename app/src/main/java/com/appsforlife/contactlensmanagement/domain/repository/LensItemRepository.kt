package com.appsforlife.contactlensmanagement.domain.repository

import androidx.lifecycle.LiveData
import com.appsforlife.contactlensmanagement.domain.entity.LensItem

interface LensItemRepository {

    suspend fun addLensItem(lensItem: LensItem)

    suspend fun deleteLensItem(lensItem: LensItem)

    fun getLensItemList(): LiveData<List<LensItem>>

}