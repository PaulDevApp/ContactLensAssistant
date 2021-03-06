package com.appsforlife.contactlensmanagement.presentation.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.appsforlife.contactlensmanagement.R
import com.appsforlife.contactlensmanagement.databinding.LayoutSplashscreenFragmentBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("CustomSplashScreen")
class SplashScreenFragment : Fragment() {

    private var _binding: LayoutSplashscreenFragmentBinding? = null
    private val binding: LayoutSplashscreenFragmentBinding
        get() = _binding ?: throw RuntimeException("LayoutInfoBinding is null")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = LayoutSplashscreenFragmentBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        startLogoAnimation()

        lifecycleScope.launch {
            launchMainFragment()
        }

        onBackPressedCallBack()
    }

    private fun startLogoAnimation() {
        with(binding) {
            tvLogo.animation = AnimationUtils.loadAnimation(
                context,
                R.anim.fall_down
            )
            ivLogo.animation = AnimationUtils.loadAnimation(
                context,
                R.anim.fall_down
            )
        }
    }

    private suspend fun launchMainFragment() {
        delay(600)
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, MainFragment.newInstance())
            .addToBackStack(MainFragment.NAME)
            .commit()
    }

    private fun onBackPressedCallBack() {
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    activity?.moveTaskToBack(true)
                }
            })
    }

    companion object {

        fun newInstance(): SplashScreenFragment {
            return SplashScreenFragment()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}