package com.appsforlife.contactlensmanagement.presentation.viewmodelfactories

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.appsforlife.contactlensmanagement.data.lensitemdata.LensItemListRepositoryImpl
import com.appsforlife.contactlensmanagement.domain.usecases.itemusecases.*
import com.appsforlife.contactlensmanagement.presentation.viewmodels.LensItemViewModel

class LensItemViewModelFactory(context: Context) : ViewModelProvider.Factory {

    private val repository by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
        LensItemListRepositoryImpl(context)
    }

    private val getLensItemListUseCase by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
        GetLensItemListUseCase(repository)
    }
    private val deleteLensItemUseCase by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
        DeleteLensItemUseCase(repository)
    }
    private val addLensItemUseCase by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
        AddLensItemUseCase(repository)
    }
    private val getLensItemCountUseCase by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
        GetLensItemCountUseCase(repository)
    }
    private val removeAllItemsUseCase by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
        RemoveAllItemsUseCase(repository)
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return LensItemViewModel(
            getLensItemListUseCase = getLensItemListUseCase,
            deleteLensItemUseCase = deleteLensItemUseCase,
            addLensItemUseCase = addLensItemUseCase,
            getLensItemCountUseCase = getLensItemCountUseCase,
            removeAllItemsUseCase = removeAllItemsUseCase
        ) as T
    }
}