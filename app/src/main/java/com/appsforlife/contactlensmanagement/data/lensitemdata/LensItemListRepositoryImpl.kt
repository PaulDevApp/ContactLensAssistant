package com.appsforlife.contactlensmanagement.data.lensitemdata

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.appsforlife.contactlensmanagement.domain.entities.LensItem
import com.appsforlife.contactlensmanagement.domain.repositories.LensItemRepository
import javax.inject.Inject

class LensItemListRepositoryImpl @Inject constructor(
    private val lensItemDao: LensItemDao,
    private val mapper: LensItemListMapper
) : LensItemRepository {

    override suspend fun addLensItem(lensItem: LensItem) {
        lensItemDao.addLensItem(mapper.mapEntityToDbModel(lensItem))
    }

    override suspend fun deleteLensItem(lensItem: LensItem) {
        lensItemDao.deleteLensItem(lensItem.id)
    }

    override fun getLensItemList(): LiveData<List<LensItem>> = Transformations.map(
        lensItemDao.getLensItemList()
    ) {
        mapper.mapListDBModelToListEntity(it)
    }

    override fun getLensItemCount(): LiveData<Int> = lensItemDao.getRowCount()

    override suspend fun removeAllItems() {
        lensItemDao.removeAllLensItems()
    }

}











