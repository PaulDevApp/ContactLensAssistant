package com.appsforlife.contactlensmanagement.presentation.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.appsforlife.contactlensmanagement.data.LensItemListRepositoryImpl
import com.appsforlife.contactlensmanagement.domain.entity.LensItem
import com.appsforlife.contactlensmanagement.domain.usecases.*
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = LensItemListRepositoryImpl(application)

    private val getLensItemListUseCase = GetLensItemListUseCase(repository)
    private val deleteLensItemUseCase = DeleteLensItemUseCase(repository)
    private val addLensItemUseCase = AddLensItemUseCase(repository)
    private val getLensItemCountUseCase = GetLensItemCountUseCase(repository)
    private val removeAllItemsUseCase = RemoveAllItemsUseCase(repository)

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







