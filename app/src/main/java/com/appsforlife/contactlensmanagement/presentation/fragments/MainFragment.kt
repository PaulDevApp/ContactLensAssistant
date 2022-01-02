package com.appsforlife.contactlensmanagement.presentation.fragments

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.*
import android.view.animation.AnimationUtils
import android.view.animation.BounceInterpolator
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.appsforlife.contactlensmanagement.R
import com.appsforlife.contactlensmanagement.databinding.LayoutMainFragmentBinding
import com.appsforlife.contactlensmanagement.databinding.LayoutToolbarMainBinding
import com.appsforlife.contactlensmanagement.domain.entity.LensItem
import com.appsforlife.contactlensmanagement.presentation.adapter.LensListAdapter
import com.appsforlife.contactlensmanagement.presentation.dialogs.DialogStartOver
import com.appsforlife.contactlensmanagement.presentation.listeners.DialogClickListener
import com.appsforlife.contactlensmanagement.presentation.utils.getCurrentDate
import com.appsforlife.contactlensmanagement.presentation.utils.getTitleCurrentDate
import com.appsforlife.contactlensmanagement.presentation.viewmodels.MainViewModel
import com.google.android.material.snackbar.Snackbar


class MainFragment : Fragment(), DialogClickListener {

    private lateinit var lensListAdapter: LensListAdapter
    private lateinit var dialogStartOver: DialogStartOver
    private lateinit var toolbarBinding: LayoutToolbarMainBinding

    private val mainViewModel: MainViewModel by viewModels()

    private var _binding: LayoutMainFragmentBinding? = null
    private val binding: LayoutMainFragmentBinding
        get() = _binding ?: throw RuntimeException("LayoutMainFragmentBinding is null")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = LayoutMainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        toolbarBinding = binding.layoutToolbar

        setUpRecyclerView()

        setUpItemClickListener()

        (activity as AppCompatActivity?)?.setSupportActionBar(binding.bottomAppbar)

        dialogStartOver = DialogStartOver(requireActivity(), this)

        mainViewModel.lensItemList.observe(viewLifecycleOwner) {
            lensListAdapter.submitList(it)
            startAnimationList()
        }

        mainViewModel.numberOfDay.observe(viewLifecycleOwner) {
            setMarkedDays(it)
            setLottieVisibility(it)
        }

        binding.fabMark.setOnClickListener {
            mainViewModel.addLensItem(LensItem(date = getCurrentDate()))
            liftUp(lensListAdapter.itemCount > 15)
            startFabAnimation()

        }

        binding.bottomAppbar.setNavigationOnClickListener {
            launchSettingsActivity()
        }

        setTitleCurrentDate()

    }

    private fun startFabAnimation() {
        val animY = ObjectAnimator.ofFloat(binding.fabMark, "translationY", -100f, 0f)
        animY.duration = 1000
        animY.interpolator = BounceInterpolator()
        animY.start()
    }

    private fun setTitleCurrentDate() {
        toolbarBinding.tvDate.text = getTitleCurrentDate()
    }

    private fun startAnimationList() {
        val animation = AnimationUtils.loadLayoutAnimation(
            requireContext(),
            R.anim.layout_animation
        )
        if (isAnim) {
            binding.rvLensItems.layoutAnimation = animation
            isAnim = false
        }
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
        Snackbar.make(
            binding.mainLayout,
            resources.getString(R.string.remove),
            Snackbar.LENGTH_SHORT
        )
            .setAnchorView(R.id.fab_mark)
            .setActionTextColor(requireActivity().getColor(R.color.colorAccentNight))
            .setAction(
                resources.getString(R.string.to_return)
            ) {
                mainViewModel.addLensItem(item)
            }.show()
    }

    private fun setUpItemClickListener() {
        lensListAdapter.onItemClickListener = {
            Toast.makeText(
                requireContext(), R.string.toast_click_item, Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun setMarkedDays(it: Int) {
        if (it > 90) {
            toolbarBinding.tvMarkedDays.setTextColor(requireActivity().getColor(R.color.extraColor))
        }
        toolbarBinding.tvMarkedDays.text = String.format(
            resources.getString(R.string.marked_days),
            it.toString()
        )
    }

    private fun liftUp(isMore: Boolean) {
        if (isMore) {
            binding.rvLensItems.smoothScrollToPosition(lensListAdapter.itemCount)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_bottom, menu)
        val menuRestartItem = menu.findItem(R.id.menu_restart)
        menuRestartItem.isVisible = false
        mainViewModel.numberOfDay.observe(viewLifecycleOwner) {
            menuRestartItem.isVisible = it > 0
        }

        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun setLottieVisibility(it: Int) {
        if (it > 0) {
            binding.lottieEmpty.visibility = View.GONE
        } else {
            binding.lottieEmpty.visibility = View.VISIBLE
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_restart -> dialogStartOver.createDialogStartOver()
            R.id.menu_note -> Toast.makeText(
                requireContext(), R.string.toast_coming_soon, Toast.LENGTH_SHORT
            ).show()
            R.id.menu_help -> launchUsefulInformationFragment()
        }
        return true
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onDialogClick() {
        mainViewModel.removeAllItems()
    }

    private fun launchSettingsActivity() {
        requireActivity().supportFragmentManager.beginTransaction()
            .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
            .replace(R.id.fragment_container, SettingsFragment.newInstance())
            .addToBackStack(null)
            .commit()
    }

    private fun launchUsefulInformationFragment() {
        requireActivity().supportFragmentManager.beginTransaction()
            .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
            .replace(R.id.fragment_container, UsefulInformationFragment.newInstance())
            .addToBackStack(null)
            .commit()
    }

    companion object {

        private var isAnim = true

        fun newInstance(): MainFragment {
            return MainFragment()
        }
    }
}