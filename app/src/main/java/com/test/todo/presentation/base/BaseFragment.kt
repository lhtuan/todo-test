package com.test.todo.presentation.base

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.test.todo.presentation.extensions.checkPermissions

abstract class BaseFragment<Binding : ViewBinding> : Fragment() {
    private var _binding: Binding? = null
    protected val binding get() = _binding!!

    private var permissionCallback: ((Boolean) -> Unit)? = null
    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) {
            val grantedAll = it.values.firstOrNull { value -> !value } == null
            permissionCallback?.invoke(grantedAll)
        }

    private var navBackStackEntry: NavBackStackEntry? = null
    private val lifecycleEventObserver = LifecycleEventObserver { _, event ->
        if (event == Lifecycle.Event.ON_RESUME) {
            onNavigationResult(navBackStackEntry?.savedStateHandle)
        }
    }

    protected abstract fun inflate(inflater: LayoutInflater, container: ViewGroup?): Binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = inflate(inflater, container)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navBackStackEntry = findNavController().currentBackStackEntry
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onResume() {
        super.onResume()
        navBackStackEntry?.lifecycle?.addObserver(lifecycleEventObserver)
    }

    override fun onPause() {
        super.onPause()
        navBackStackEntry?.lifecycle?.removeObserver(lifecycleEventObserver)
    }

    override fun onDestroy() {
        permissionCallback = null
        super.onDestroy()
    }

    /**
     * @param onNavBackClicked handle event when click on nav back, it will go back by default
     */
    protected fun setSupportActionBar(
        toolbar: Toolbar?,
        isShowTitle: Boolean = false,
        onNavBackClicked: (() -> Unit)? = null
    ) {
        (activity as AppCompatActivity).apply {
            setSupportActionBar(toolbar)
            supportActionBar?.setDisplayShowTitleEnabled(isShowTitle)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            toolbar?.setNavigationOnClickListener {
                onNavBackClicked?.invoke() ?: run {
                    findNavController().navigateUp()
                }
            }
        }
    }

    /**
     * Check if all permissions are granted, if any isn't then ask user for granting permissions
     */
    protected fun enforcePermissions(
        permissions: Array<String>,
        maxSDK: Int? = null,
        callback: (Boolean) -> Unit
    ) {
        if (maxSDK != null && maxSDK < Build.VERSION.SDK_INT) {
            //don't need to request permission
            callback.invoke(true)
        } else {
            val unGrantedPermissions = requireContext().checkPermissions(permissions)
            if (unGrantedPermissions.isEmpty()) {
                callback.invoke(true)
            } else {
                this.permissionCallback = callback
                requestPermissionLauncher.launch(unGrantedPermissions)
            }
        }
    }

    protected open fun onNavigationResult(savedStateHandle: SavedStateHandle?) {
        //Handle result from previous fragment here
    }

    /**
     * Handle hard back button pressed event
     */
    protected fun customBackPress(action: () -> Unit) {
        requireActivity().onBackPressedDispatcher.addCallback(this, object :
            OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                action.invoke()
            }
        })
    }
}