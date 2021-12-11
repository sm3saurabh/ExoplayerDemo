package dev.saurabhmishra.exoplayersample.base

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

abstract class BaseActivity<VM: ViewModel, VB: ViewDataBinding>: AppCompatActivity(), BaseUIComponent<VM, VB> {

    private var _binding: VB? = null

    override val binding: VB
        get() = _binding!!

    override lateinit var viewModel: VM

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)

        _binding = DataBindingUtil.setContentView(this, layoutId())
        viewModel = ViewModelProvider(this)[viewModelClass()]
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}