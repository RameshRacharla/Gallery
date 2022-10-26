package com.application.ui.base

import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.application.AppController
import com.application.di.component.ActivityComponent
import com.application.di.component.DaggerActivityComponent
import com.application.di.module.ActivityModule
import com.application.utils.common.ProgressDialog

import javax.inject.Inject


/**
 * Reference for generics: https://kotlinlang.org/docs/reference/generics.html
 * Basically BaseActivity will take any class that extends BaseViewModel
 */
abstract class BaseActivity<VM : BaseViewModel> : AppCompatActivity() {

    @Inject
    lateinit var viewModel: VM

    lateinit var dialog: Dialog

    lateinit var windowInsetsController: WindowInsetsControllerCompat

    override fun onCreate(savedInstanceState: Bundle?) {
        injectDependencies(buildActivityComponent())
        super.onCreate(savedInstanceState)
        setContentView(provideLayoutId())
        hideSystemBars()
        setupObservers()
        setupView(savedInstanceState)
        initProgressDialog()
        viewModel.onCreate()
        hideSystemBars()

//        mSocket.on(SocketOnConstants.RECEIVE_REQ_DUAL_PROGRAM, onReceiveListener)
    }

    private fun buildActivityComponent() =
        DaggerActivityComponent
            .builder()
            .applicationComponent((application as AppController).applicationComponent)
            .activityModule(ActivityModule(this))
            .build()

    protected open fun setupObservers() {
        dialog = ProgressDialog.progressDialog(this)

        viewModel.messageString.observe(this) {

            it.data?.run { showMessage(this) }
        }

        viewModel.messageStringId.observe(this) {
            it.data?.run { showMessage(this) }
        }


    }


    /**
     * Used to initiate the progress dialog from onCreate
     */
    protected fun initProgressDialog() {
        try {
            dialog = ProgressDialog.progressDialog(this)
            dialog.setCancelable(false)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /**
     * Used to show the progress dialog if its not       showing
     * 'progressInstances' will be having the number of progress dialog showing occurrences.
     * It will be incremented while showing and decremented when a progress dialog completes its necessity
     */
    protected fun showProgressDialog() {
        try {
            initProgressDialog()
            if (!dialog.isShowing)
                dialog.show()
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
    }

    /**
     * Used to dismiss the progress dialog if its showing
     * 'progressInstances' will be having the number of progress dialog showing occurrences.
     * It will be incremented while showing and decremented when a progress dialog completes its necessity
     */
    protected fun dismissProgressDialog() {
        try {
            if (dialog.isShowing) {
                dialog.dismiss()
            }
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
    }

    /**
     * Showing Alert Dialog with Settings option
     * Navigates user to app settings
     * NOTE: Keep proper title and message depending on your app
     */
    protected fun showSettingsDialog() {
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        builder.setTitle("Need Permissions")
        builder.setMessage("This app needs permission to use this feature. You can grant them in app settings.")
        builder.setPositiveButton(
            "GOTO SETTINGS"
        ) { dialog, _ ->
            dialog.cancel()
            openSettings()
        }
        builder.setNegativeButton(
            "Cancel"
        ) { dialog, _ -> dialog.cancel() }
        builder.show()
    }

    // navigating user to app settings
    private fun openSettings() {
        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
        val uri: Uri = Uri.fromParts("package", packageName, null)
        intent.data = uri
        startActivityForResult(intent, 101)
    }

    fun showMessage(message: String) =
        Toast.makeText(applicationContext, message, Toast.LENGTH_LONG).show()

    fun showMessage(@StringRes resId: Int) = showMessage(getString(resId))

    fun hideKeyboard(view: View) {
        val inputMethodManager = view.context.getSystemService(Activity.INPUT_METHOD_SERVICE)
                as InputMethodManager

        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

    open fun goBack() = onBackPressedDispatcher.onBackPressed()


    private val onBackPressed = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            // Your business logic to handle the back pressed event
            finish()
        }
    }

    private fun hideSystemBars() {
        windowInsetsController =
            ViewCompat.getWindowInsetsController(window.decorView) ?: return
        // Configure the behavior of the hidden system bars
        windowInsetsController.systemBarsBehavior =
            WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        // Hide both the status bar and the navigation bar
//        windowInsetsController.hide(WindowInsetsCompat.Type.systemBars())
    }

    @LayoutRes
    protected abstract fun provideLayoutId(): Int

    protected abstract fun injectDependencies(activityComponent: ActivityComponent)

    protected abstract fun setupView(savedInstanceState: Bundle?)
}