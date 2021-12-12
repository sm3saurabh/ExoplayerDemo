package dev.saurabhmishra.exoplayersample.extensions

import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import java.lang.ref.WeakReference
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

/**
 * @author Saurabh Mishra
 * Use this to have a lifecycle aware interaction model between a fragment and it's parent
 */
class FragmentParentDelegate<T>(
    private val fragmentReference: WeakReference<Fragment>
): ReadOnlyProperty<Fragment, T?>, DefaultLifecycleObserver {

    private var delegate: T? = null

    private val fragment: Fragment?
        get() = fragmentReference.get()

    init {
        // A listener is active in association with the host enabled lifecycle of an activity
        // Not the one associated with a fragment's view
        fragment?.lifecycle?.addObserver(this)
    }

    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        delegate = fragment?.parentFragment?.let { parent ->
            parent as? T?
        } ?: fragment?.context as? T?
    }

    override fun onDestroy(owner: LifecycleOwner) {
        delegate = null
    }

    override fun getValue(thisRef: Fragment, property: KProperty<*>): T? {
        return delegate
    }
}

/**
 * @author Saurabh Mishra
 */
inline fun <reified T: Any?> Fragment.fragmentDelegate(): FragmentParentDelegate<T> {
    return FragmentParentDelegate(WeakReference(this))
}