package com.appsforlife.contactlensmanagement.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.ListAdapter
import com.appsforlife.contactlensmanagement.R
import com.appsforlife.contactlensmanagement.databinding.*
import com.appsforlife.contactlensmanagement.domain.entities.LensItem
import com.appsforlife.contactlensmanagement.presentation.utils.LensItemDiffCallBack
import com.appsforlife.contactlensmanagement.presentation.viewholder.LensItemViewHolder

class LensListAdapter : ListAdapter<LensItem, LensItemViewHolder>(LensItemDiffCallBack()) {

    var onItemClickListener: (() -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LensItemViewHolder {

        val layout = when (viewType) {
            VIEW_TYPE_TWO_WEEKS -> R.layout.lens_item_two_weeks
            VIEW_TYPE_TRANSITION_FROM_2_WEEKS_TO_1_MONTH -> R.layout.lens_item_transition_from_2_weeks_to_1_month
            VIEW_TYPE_ONE_MONTH -> R.layout.lens_item_one_month
            VIEW_TYPE_TRANSITION_FROM_1_MONTH_TO_3_MONTHS -> R.layout.lens_item_transition_from_1_month_to_3_months
            VIEW_TYPE_THREE_MONTHS -> R.layout.lens_item_three_months
            VIEW_TYPE_TRANSITION_FROM_3_MONTHS_TO_EXTRA -> R.layout.lens_item_transition_from_3_months_to_extra
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
        val binding = holder.binding

        binding.root.setOnClickListener {
            onItemClickListener?.invoke()
        }

        when (binding) {
            is LensItemTwoWeeksBinding -> {
                binding.lensItem = lensItem
            }
            is LensItemTransitionFrom2WeeksTo1MonthBinding -> {
                binding.lensItem = lensItem
            }
            is LensItemOneMonthBinding -> {
                binding.lensItem = lensItem
            }
            is LensItemTransitionFrom1MonthTo3MonthsBinding -> {
                binding.lensItem = lensItem
            }
            is LensItemThreeMonthsBinding -> {
                binding.lensItem = lensItem
            }
            is LensItemTransitionFrom3MonthsToExtraBinding -> {
                binding.lensItem = lensItem
            }
            is LensItemExtraBinding -> {
                binding.lensItem = lensItem
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when {
            position <= 12 -> {
                VIEW_TYPE_TWO_WEEKS
            }
            position == 13 -> {
                VIEW_TYPE_TRANSITION_FROM_2_WEEKS_TO_1_MONTH
            }
            position in 14..28 -> {
                VIEW_TYPE_ONE_MONTH
            }
            position == 29 -> {
                VIEW_TYPE_TRANSITION_FROM_1_MONTH_TO_3_MONTHS
            }
            position in 30..88 -> {
                VIEW_TYPE_THREE_MONTHS
            }
            position == 89 -> {
                VIEW_TYPE_TRANSITION_FROM_3_MONTHS_TO_EXTRA
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
        const val VIEW_TYPE_TRANSITION_FROM_2_WEEKS_TO_1_MONTH = 4
        const val VIEW_TYPE_TRANSITION_FROM_1_MONTH_TO_3_MONTHS = 5
        const val VIEW_TYPE_TRANSITION_FROM_3_MONTHS_TO_EXTRA = 6
    }
}