package com.appsforlife.contactlensmanagement.data

import com.appsforlife.contactlensmanagement.domain.entity.LensItem

class LensItemListMapper {

    fun mapEntityToDbModel(lensItem: LensItem) = LensItemDbModel(
        id = lensItem.id,
        date = lensItem.date
    )

    fun mapDbModelToEntity(lensItemDbModel: LensItemDbModel) = LensItem(
        id = lensItemDbModel.id,
        date = lensItemDbModel.date
    )

    fun mapListDBModelToListEntity(list: List<LensItemDbModel>) = list.map {
        mapDbModelToEntity(it)
    }
}