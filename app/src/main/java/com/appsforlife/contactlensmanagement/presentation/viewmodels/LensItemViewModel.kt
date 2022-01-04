package com.appsforlife.contactlensmanagement.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.appsforlife.contactlensmanagement.domain.entities.LensItem
import com.appsforlife.contactlensmanagement.domain.usecases.itemusecases.*
import kotlinx.coroutines.launch

class LensItemViewModel(
    getLensItemListUseCase: GetLensItemListUseCase,
    private val deleteLensItemUseCase: DeleteLensItemUseCase,
    private val addLensItemUseCase: AddLensItemUseCase,
    getLensItemCountUseCase: GetLensItemCountUseCase,
    private val removeAllItemsUseCase: RemoveAllItemsUseCase
) : ViewModel() {

    val lensItemList = getLensItemListUseCase.getLensList()
    val numberOfDay = getLensItemCountUseCase.getLensItemCount()

    fun deleteLensItem(lensItem: LensItem) {
        viewModelScope.launch {
            deleteLensItemUseCase.deleteLensItem(lensItem)
        }
    }

    fun removeAllItems() {
        viewModelScope.launch {
            removeAllItemsUseCase.removeAllCounts()
        }
    }

    fun addLensItem(lensItem: LensItem) {
        viewModelScope.launch {
            addLensItemUseCase.addLensItem(lensItem)
        }
    }
}







