package com.mfahimi.tvshowmanager.ui.activity

import androidx.viewbinding.ViewBinding
import com.mfahimi.tvshowmanager.databinding.ActivityMainBinding

class MainActivity : BaseActivity() {
    override val binding: ViewBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
}