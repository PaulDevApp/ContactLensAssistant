package com.appsforlife.contactlensmanagement.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.ListAdapter
import com.appsforlife.contactlensmanagement.R
import com.appsforlife.contactlensmanagement.databinding.LensItemOneMonthBinding
import com.appsforlife.contactlensmanagement.databinding.LensItemTwoWeekBinding
import com.appsforlife.contactlensmanagement.domain.entity.LensItem
import com.appsforlife.contactlensmanagement.presentation.utils.LensItemDiffCallBack
import com.appsforlife.contactlensmanagement.presentation.viewholder.LensItemViewHolder

class LensListAdapter : ListAdapter<LensItem, LensItemViewHolder>(LensItemDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LensItemViewHolder {

        val layout = when (viewType) {
            VIEW_TYPE_TWO_WEEK -> R.layout.lens_item_two_week
            VIEW_TYPE_ONE_MONTH -> R.layout.lens_item_one_month
            else -> throw RuntimeException("Unknown view type: $viewType")
        }

        val binding = DataBindingUtil.inflate<ViewDataBinding>(
            LayoutInflater.from(parent.context),
            layout,
            parent,
            false
        )
        return LensItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LensItemViewHolder, position: Int) {
        val lensItem = getItem(position)
        when (val binding = holder.binding) {
            is LensItemTwoWeekBinding -> {
                binding.lensItem = lensItem
            }
            is LensItemOneMonthBinding -> {
                binding.lensItem = lensItem
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        val item = getItem(position)
        return if (item.id > 14) {
            VIEW_TYPE_TWO_WEEK
        } else {
            VIEW_TYPE_ONE_MONTH
        }
    }

    companion object {
        const val VIEW_TYPE_TWO_WEEK = 0
        const val VIEW_TYPE_ONE_MONTH = 1
    }


}