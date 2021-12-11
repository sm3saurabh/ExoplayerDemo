package dev.saurabhmishra.exoplayersample.base

import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel

interface BaseUIComponent<VM: ViewModel, VB: ViewDataBinding> {
    val binding: VB
    val viewModel: VM

    @LayoutRes
    fun layoutId(): Int
    fun viewModelClass(): Class<VM>

}