package dev.saurabhmishra.exoplayersample.base

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.hilt.android.AndroidEntryPoint

abstract class BaseActivity<VM: BaseViewModel, VB: ViewDataBinding>: AppCompatActivity(), BaseUIComponent<VM, VB> {

    private var _binding: VB? = null

    override val binding: VB
        get() = _binding!!

    override lateinit var viewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this)[viewModelClass()]
        _binding = DataBindingUtil.setContentView(this, layoutId())

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}