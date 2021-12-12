package dev.saurabhmishra.exoplayersample.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

abstract class BaseFragment<VM: BaseViewModel, VB: ViewDataBinding> : Fragment(), BaseUIComponent<VM, VB> {

    private var _binding: VB? = null

    override val binding: VB
        get() = _binding!!

    override lateinit var viewModel: VM

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater, layoutId(), container, false)
        viewModel = ViewModelProvider(this)[viewModelClass()]

        return _binding?.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}