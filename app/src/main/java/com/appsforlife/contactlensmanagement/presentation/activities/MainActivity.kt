package com.appsforlife.contactlensmanagement.presentation.activities

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.appsforlife.contactlensmanagement.R
import com.appsforlife.contactlensmanagement.databinding.ActivityMainBinding
import com.appsforlife.contactlensmanagement.databinding.LayoutToolbarMainBinding
import com.appsforlife.contactlensmanagement.domain.entity.LensItem
import com.appsforlife.contactlensmanagement.presentation.adapter.LensListAdapter
import com.appsforlife.contactlensmanagement.presentation.dialogs.DialogStartOver
import com.appsforlife.contactlensmanagement.presentation.listeners.DialogClickListener
import com.appsforlife.contactlensmanagement.presentation.utils.getCurrentDate
import com.appsforlife.contactlensmanagement.presentation.utils.getTitleCurrentDate
import com.appsforlife.contactlensmanagement.presentation.viewmodels.MainViewModel
import com.google.android.material.snackbar.Snackbar


class MainActivity : AppCompatActivity(), DialogClickListener {


    private lateinit var mainViewModel: MainViewModel
    private lateinit var lensListAdapter: LensListAdapter
    private lateinit var dialogStartOver: DialogStartOver

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private lateinit var toolbarBinding: LayoutToolbarMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()

        toolbarBinding = binding.layoutToolbar
        setContentView(binding.root)

        setUpRecyclerView()

        setUpItemClickListener()

        setSupportActionBar(binding.bottomAppbar)

        dialogStartOver = DialogStartOver(this, this)

        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]

        mainViewModel.lensItemList.observe(this) {
            lensListAdapter.submitList(it)
            startAnimationList()
        }

        mainViewModel.numberOfDay.observe(this) {
            setMarkedDays(it)
            setLottieVisibility(it)
        }

        binding.fabMark.setOnClickListener {
            mainViewModel.addLensItem(LensItem(date = getCurrentDate()))
            liftUp(lensListAdapter.itemCount > 15)
        }

        binding.bottomAppbar.setNavigationOnClickListener {
            Toast.makeText(this, R.string.toast_coming_soon, Toast.LENGTH_SHORT).show()
        }

        setTitleCurrentDate()

    }

    private fun setTitleCurrentDate() {
        toolbarBinding.tvDate.text = getTitleCurrentDate()
    }

    private fun setLottieVisibility(it: Int) {
        if (it > 0) {
            binding.lottieEmpty.visibility = View.GONE
        } else {
            binding.lottieEmpty.visibility = View.VISIBLE
        }
    }

    private fun setMarkedDays(it: Int) {
        if (it > 90) {
            toolbarBinding.tvMarkedDays.setTextColor(applicationContext.getColor(R.color.extraColor))
        }
        toolbarBinding.tvMarkedDays.text = String.format(resources.getString(R.string.marked_days),
            it.toString())
    }

    private fun liftUp(isMore: Boolean) {
        if (isMore) {
            binding.rvLensItems.smoothScrollToPosition(lensListAdapter.itemCount)
        }
    }

    private fun startAnimationList() {
        val animation = AnimationUtils.loadLayoutAnimation(this,
            R.anim.layout_animation)
        if (isAnim) {
            binding.rvLensItems.layoutAnimation = animation
            isAnim = false
        }
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_bottom, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_restart -> dialogStartOver.createDialogStartOver()
            R.id.menu_note -> Toast.makeText(
                this, R.string.toast_coming_soon, Toast.LENGTH_SHORT).show()
            R.id.menu_help -> Toast.makeText(
                this, R.string.toast_coming_soon, Toast.LENGTH_SHORT).show()
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
                setSnackBar(item)
            }
        }
        val itemTouchHelper = ItemTouchHelper(callBack)
        itemTouchHelper.attachToRecyclerView(rvLensItemList)
    }

    private fun setSnackBar(item: LensItem) {
        Snackbar.make(findViewById(R.id.main_layout),
            resources.getString(R.string.remove),
            Snackbar.LENGTH_SHORT)
            .setAnchorView(R.id.fab_mark)
            .setActionTextColor(getColor(R.color.colorAccentNight))
            .setAction(resources.getString(R.string.to_return)
            ) {
                mainViewModel.addLensItem(item)
            }.show()
    }

    private fun setUpItemClickListener() {
        lensListAdapter.onItemClickListener = {
            Toast.makeText(
                this, R.string.toast_click_item, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDialogClick() {
        mainViewModel.removeAllItems()
    }

    companion object {
        private var isAnim = true
    }
}





