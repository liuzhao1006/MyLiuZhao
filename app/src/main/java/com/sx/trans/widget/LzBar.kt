package com.lz.cloud.widget

import android.annotation.TargetApi
import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.support.annotation.ColorInt
import android.util.DisplayMetrics
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.FrameLayout

/**
 * 作者 : 刘朝,
 * on 2017/8/29,
 * GitHub : https://github.com/liuzhao1006
 */

class LzBar(private val activity: Activity) {

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @JvmOverloads
    fun setColorBar(@ColorInt color: Int, alpha: Int = 0) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window = activity.window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)
            val alphaColor = if (alpha == 0) color else calculateColor(color, alpha)
            window.statusBarColor = alphaColor
            window.navigationBarColor = alphaColor
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            val window = activity.window
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            val alphaColor = if (alpha == 0) color else calculateColor(color, alpha)
            val decorView = window.decorView as ViewGroup
            decorView.addView(createStatusBarView(activity, alphaColor))
            if (navigationBarExist(activity)) {
                decorView.addView(createNavBarView(activity, alphaColor))
                window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)
            }
            setRootView(activity, true)
        }
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @JvmOverloads
    fun setNavigationColorBar(@ColorInt color: Int, alpha: Int = 0) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window = activity.window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)
            val alphaColor = if (alpha == 0) color else calculateColor(color, alpha)
            window.navigationBarColor = alphaColor
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            val window = activity.window
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            val alphaColor = if (alpha == 0) color else calculateColor(color, alpha)
            val decorView = window.decorView as ViewGroup
            if (navigationBarExist(activity)) {
                decorView.addView(createNavBarView(activity, alphaColor))
                window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)
            }
            setRootView(activity, true)
        }
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @JvmOverloads
    fun setColorBarForDrawer(@ColorInt color: Int, alpha: Int = 0) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window = activity.window
            val decorView = window.decorView as ViewGroup
            var option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            if (navigationBarExist(activity)) {
                option = option or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
            }
            decorView.systemUiVisibility = option
            window.navigationBarColor = Color.TRANSPARENT
            window.statusBarColor = Color.TRANSPARENT
            val alphaColor = if (alpha == 0) color else calculateColor(color, alpha)
            decorView.addView(createStatusBarView(activity, alphaColor), 0)
            if (navigationBarExist(activity)) {
                decorView.addView(createNavBarView(activity, alphaColor), 1)
            }
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            val window = activity.window
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            val decorView = window.decorView as ViewGroup
            val alphaColor = if (alpha == 0) color else calculateColor(color, alpha)
            decorView.addView(createStatusBarView(activity, alphaColor), 0)
            if (navigationBarExist(activity)) {
                window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)
                decorView.addView(createNavBarView(activity, alphaColor), 1)
            }
        }
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    fun setTransparentBar(@ColorInt color: Int, alpha: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window = activity.window
            val decorView = window.decorView
            val option =  View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            decorView.systemUiVisibility = option

            val finalColor = if (alpha == 0)
                Color.TRANSPARENT
            else
                Color.argb(alpha, Color.red(color), Color.green(color), Color.blue(color))

            window.statusBarColor = finalColor

        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            val window = activity.window
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            val decorView = window.decorView as ViewGroup
            val finalColor = if (alpha == 0)
                Color.TRANSPARENT
            else
                Color.argb(alpha, Color.red(color), Color.green(color), Color.blue(color))
            decorView.addView(createStatusBarView(activity, finalColor))
        }

    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    fun setImmersionBar() {
        setTransparentBar(Color.TRANSPARENT, 0)
    }


    @TargetApi(Build.VERSION_CODES.KITKAT)
    fun setHintBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            val decorView = activity.window.decorView
            decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_FULLSCREEN or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        }
    }

    private fun createStatusBarView(context: Context, @ColorInt color: Int): View {
        val mStatusBarTintView = View(context)
        val params = FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, getStatusBarHeight(context))
        params.gravity = Gravity.TOP
        mStatusBarTintView.layoutParams = params
        mStatusBarTintView.setBackgroundColor(color)
        return mStatusBarTintView
    }

    public fun createNavBarView(context: Context, @ColorInt color: Int): View {
        val mNavBarTintView = View(context)
        val params = FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, getNavigationHeight(context))
        params.gravity = Gravity.BOTTOM
        mNavBarTintView.layoutParams = params
        mNavBarTintView.setBackgroundColor(color)
        return mNavBarTintView
    }

    private fun navigationBarExist(activity: Activity): Boolean {
        val windowManager = activity.windowManager
        val d = windowManager.defaultDisplay

        val realDisplayMetrics = DisplayMetrics()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            d.getRealMetrics(realDisplayMetrics)
        }

        val realHeight = realDisplayMetrics.heightPixels
        val realWidth = realDisplayMetrics.widthPixels

        val displayMetrics = DisplayMetrics()
        d.getMetrics(displayMetrics)

        val displayHeight = displayMetrics.heightPixels
        val displayWidth = displayMetrics.widthPixels

        return realWidth - displayWidth > 0 || realHeight - displayHeight > 0
    }

    @ColorInt
    private fun calculateColor(@ColorInt color: Int, alpha: Int): Int {
        val a = 1 - alpha / 255f
        var red = color shr 16 and 0xff
        var green = color shr 8 and 0xff
        var blue = color and 0xff
        red = (red * a + 0.5).toInt()
        green = (green * a + 0.5).toInt()
        blue = (blue * a + 0.5).toInt()
        return 0xff shl 24 or (red shl 16) or (green shl 8) or blue
    }

    private fun setRootView(activity: Activity, fit: Boolean) {
        val parent = activity.findViewById<View>(android.R.id.content) as ViewGroup
        var i = 0
        val count = parent.childCount
        while (i < count) {
            val childView = parent.getChildAt(i)
            if (childView is ViewGroup) {
                childView.setFitsSystemWindows(fit)
                childView.clipToPadding = fit
            }
            i++
        }
    }

    private fun getStatusBarHeight(context: Context): Int {
        val resourceId = context.resources.getIdentifier("status_bar_height", "dimen", "android")
        return context.resources.getDimensionPixelSize(resourceId)
    }

    fun getNavigationHeight(context: Context): Int {

        val resourceId = context.resources.getIdentifier("navigation_bar_height", "dimen", "android")
        return context.resources.getDimensionPixelSize(resourceId)
    }
}
