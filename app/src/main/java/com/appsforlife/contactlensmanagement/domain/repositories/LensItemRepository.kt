package com.appsforlife.contactlensmanagement.domain.repositories

import androidx.lifecycle.LiveData
import com.appsforlife.contactlensmanagement.domain.entities.LensItem

interface LensItemRepository {

    suspend fun addLensItem(lensItem: LensItem)

    suspend fun deleteLensItem(lensItem: LensItem)

    fun getLensItemList(): LiveData<List<LensItem>>

    fun getLensItemCount() : LiveData<Int>

    suspend fun removeAllItems()
}