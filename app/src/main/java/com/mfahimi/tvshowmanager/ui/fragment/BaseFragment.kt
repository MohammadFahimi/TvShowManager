package com.mfahimi.tvshowmanager.ui.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding


abstract class BaseFragment<T : ViewBinding> : Fragment() {

    private var _binding: T? = null
    protected val binding get() = _binding!!
    abstract val viewBinding: T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initObjects(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = viewBinding
        initViews(binding.root, savedInstanceState)
        return binding.root
    }

    protected open fun initObjects(savedInstanceState: Bundle?) = Unit
    protected open fun initViews(view: View, savedInstanceState: Bundle?) = Unit

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}