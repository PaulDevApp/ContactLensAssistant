package com.appsforlife.contactlensmanagement.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.ListAdapter
import com.appsforlife.contactlensmanagement.R
import com.appsforlife.contactlensmanagement.databinding.*
import com.appsforlife.contactlensmanagement.domain.entity.LensItem
import com.appsforlife.contactlensmanagement.presentation.utils.LensItemDiffCallBack
import com.appsforlife.contactlensmanagement.presentation.viewholder.LensItemViewHolder

class LensListAdapter : ListAdapter<LensItem, LensItemViewHolder>(LensItemDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LensItemViewHolder {

        val layout = when (viewType) {
            VIEW_TYPE_TWO_WEEKS -> R.layout.lens_item_two_weeks
            VIEW_TYPE_ONE_MONTH -> R.layout.lens_item_one_month
            VIEW_TYPE_THREE_MONTHS -> R.layout.lens_item_three_months
            VIEW_TYPE_EXTRA -> R.layout.lens_item_extra
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
            is LensItemTwoWeeksBinding -> {
                binding.lensItem = lensItem
            }
            is LensItemOneMonthBinding -> {
                binding.lensItem = lensItem
            }
            is LensItemThreeMonthsBinding -> {
                binding.lensItem = lensItem
            }
            is LensItemExtraBinding -> {
                binding.lensItem = lensItem
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when {
            itemCount <= 14 -> {
                VIEW_TYPE_TWO_WEEKS
            }
            itemCount in 15..30 -> {
                VIEW_TYPE_ONE_MONTH
            }
            itemCount in 31..90 -> {
                VIEW_TYPE_THREE_MONTHS
            }
            else -> {
                VIEW_TYPE_EXTRA
            }
        }
    }

    companion object {
        const val VIEW_TYPE_TWO_WEEKS = 0
        const val VIEW_TYPE_ONE_MONTH = 1
        const val VIEW_TYPE_THREE_MONTHS = 2
        const val VIEW_TYPE_EXTRA = 3
    }
}