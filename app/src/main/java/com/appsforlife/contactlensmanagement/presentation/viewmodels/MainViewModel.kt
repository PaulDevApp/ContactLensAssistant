package com.appsforlife.contactlensmanagement.presentation.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.appsforlife.contactlensmanagement.data.LensItemListRepositoryImpl
import com.appsforlife.contactlensmanagement.domain.entity.LensItem
import com.appsforlife.contactlensmanagement.domain.usecases.AddLensItemUseCase
import com.appsforlife.contactlensmanagement.domain.usecases.DeleteLensItemUseCase
import com.appsforlife.contactlensmanagement.domain.usecases.GetLensItemListUseCase
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = LensItemListRepositoryImpl(application)

    private val getLensItemListUseCase = GetLensItemListUseCase(repository)
    private val deleteLensItemUseCase = DeleteLensItemUseCase(repository)
    private val addLensItemUseCase = AddLensItemUseCase(repository)

    val lensItemList = getLensItemListUseCase.getLensList()

    fun deleteLensItem(lensItem: LensItem){
        viewModelScope.launch {
            deleteLensItemUseCase.deleteLensItem(lensItem)
        }
    }

    fun addLensItem(){
        viewModelScope.launch {
            val lensItem = LensItem(date = "25.10.2021")
            addLensItemUseCase.addLensItem(lensItem)
        }
    }
}







