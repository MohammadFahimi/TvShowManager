package com.mfahimi.tvshowmanager.ui.fragment

import android.os.Bundle
import android.view.View
import com.mfahimi.tvshowmanager.databinding.FragmentHomeBinding

class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    override val viewBinding: FragmentHomeBinding by lazy { FragmentHomeBinding.inflate(layoutInflater) }

    override fun initViews(view: View, savedInstanceState: Bundle?) = with(binding) {

    }
}