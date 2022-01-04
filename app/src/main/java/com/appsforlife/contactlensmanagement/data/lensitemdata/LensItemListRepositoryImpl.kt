package com.appsforlife.contactlensmanagement.data.lensitemdata

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.appsforlife.contactlensmanagement.data.AppDataBase
import com.appsforlife.contactlensmanagement.domain.entities.LensItem
import com.appsforlife.contactlensmanagement.domain.repositories.LensItemRepository

class LensItemListRepositoryImpl(
    context: Context,
) : LensItemRepository {

    private val lensItemDao = AppDataBase.getInstance(context).lensItemDao()
    private val mapper = LensItemListMapper()

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











