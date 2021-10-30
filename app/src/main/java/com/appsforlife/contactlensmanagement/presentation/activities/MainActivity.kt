package com.appsforlife.contactlensmanagement.presentation.activities

import android.content.Intent
import android.os.Bundle
import android.speech.RecognizerIntent
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.appsforlife.contactlensmanagement.R
import com.appsforlife.contactlensmanagement.databinding.ActivityMainBinding
import com.appsforlife.contactlensmanagement.presentation.adapter.LensListAdapter
import com.appsforlife.contactlensmanagement.presentation.viewmodels.MainViewModel
import java.lang.Exception
import java.util.*


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mainViewModel: MainViewModel
    private lateinit var lensListAdapter: LensListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpRecyclerView()

        setSupportActionBar(binding.bottomAppbar)

        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        val animation = AnimationUtils.loadLayoutAnimation(this,
            R.anim.layout_animation)

        mainViewModel.lensItemList.observe(this) {
            lensListAdapter.submitList(it)
            binding.rvLensItems.layoutAnimation = animation
        }

        mainViewModel.numberOfDay.observe(this) {
            binding.toolbar.subtitle = String.format(resources.getString(R.string.marked_days),
                it.toString())
        }

        binding.fabMark.setOnClickListener {
            mainViewModel.addLensItem()
            if (lensListAdapter.itemCount > 15) {
                binding.rvLensItems.smoothScrollToPosition(lensListAdapter.itemCount - 1)
            }
        }

        binding.bottomAppbar.setNavigationOnClickListener {
            Toast.makeText(this, R.string.coming_soon, Toast.LENGTH_SHORT).show()
        }

    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_bottom, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_restart -> mainViewModel.removeAllItems()
            R.id.menu_note -> Toast.makeText(
                this, R.string.coming_soon, Toast.LENGTH_SHORT).show()
        }
        return true
    }


    private fun setUpRecyclerView() {
        lensListAdapter = LensListAdapter()
        binding.rvLensItems.adapter = lensListAdapter
        binding.rvLensItems.setHasFixedSize(true)
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





