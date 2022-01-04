package com.appsforlife.contactlensmanagement.data.lensitemdata

import com.appsforlife.contactlensmanagement.domain.entities.LensItem

class LensItemListMapper {

    fun mapEntityToDbModel(lensItem: LensItem) = LensItemDbModel(
        id = lensItem.id,
        date = lensItem.date
    )

    private fun mapDbModelToEntity(lensItemDbModel: LensItemDbModel) = LensItem(
        id = lensItemDbModel.id,
        date = lensItemDbModel.date
    )

    fun mapListDBModelToListEntity(list: List<LensItemDbModel>) = list.map {
        mapDbModelToEntity(it)
    }
}