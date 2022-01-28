package com.appsforlife.contactlensmanagement.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import com.appsforlife.contactlensmanagement.R
import com.appsforlife.contactlensmanagement.databinding.LayoutInfoFragmentBinding

class UsefulInformationFragment : Fragment() {

    private var _binding: LayoutInfoFragmentBinding? = null
    private val binding: LayoutInfoFragmentBinding
        get() = _binding ?: throw RuntimeException("LayoutInfoBinding is null")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = LayoutInfoFragmentBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpToolbar()

        onBackPressedCallBack()
    }

    private fun setUpToolbar() {
        binding.toolbarInfo.tvToolbarTitle.text =
            requireActivity().resources.getString(R.string.useful_information)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun onBackPressedCallBack() {
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    backPresses()
                }
            })
    }

    private fun backPresses() {
        requireActivity().supportFragmentManager.popBackStack(MainFragment.NAME, 0)
    }

    companion object {

        const val NAME = "UsefulInformationFragment"

        fun newInstance(): UsefulInformationFragment {
            return UsefulInformationFragment()
        }
    }
}