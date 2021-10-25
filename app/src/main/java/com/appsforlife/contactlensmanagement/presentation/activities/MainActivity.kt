package com.appsforlife.contactlensmanagement.presentation.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.appsforlife.contactlensmanagement.R
import com.appsforlife.contactlensmanagement.databinding.ActivityMainBinding
import com.appsforlife.contactlensmanagement.presentation.adapter.LensListAdapter
import com.appsforlife.contactlensmanagement.presentation.viewmodels.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mainViewModel: MainViewModel
    private lateinit var lensListAdapter: LensListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpRecyclerView()

        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        mainViewModel.lensItemList.observe(this) {
            lensListAdapter.submitList(it)
        }

        binding.fabMark.setOnClickListener {
            mainViewModel.addLensItem()
        }
    }

    private fun setUpRecyclerView() {
        lensListAdapter = LensListAdapter()
        binding.rvLensItems.adapter = lensListAdapter

        setUpItemTouchHelper(binding.rvLensItems)
    }

    private fun setUpItemTouchHelper(rvLensItemList: RecyclerView) {
        val callBack = object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder,
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val item = lensListAdapter.currentList[viewHolder.adapterPosition]
                mainViewModel.deleteLensItem(item)
            }
        }
        val itemTouchHelper = ItemTouchHelper(callBack)
        itemTouchHelper.attachToRecyclerView(rvLensItemList)
    }
}





