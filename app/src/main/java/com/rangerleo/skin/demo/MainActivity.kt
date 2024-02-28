package com.rangerleo.skin.demo

import android.annotation.SuppressLint
import android.app.ActionBar.LayoutParams
import android.app.AlertDialog
import android.os.Bundle
import android.view.ViewGroup
import android.widget.PopupWindow
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.rangerleo.skin.core.SkinManager
import com.rangerleo.skin.ktx.addOnSkinChangeListener
import com.rangerleo.skin.ktx.getDimension
import com.rangerleo.skin.ktx.getString
import com.rangerleo.skin.ktx.setText2
import com.rangerleo.skin.ktx.setTextColor2
import com.rangerleo.skin.ktx.setTextSize2
import io.github.ranger.skin.R
import io.github.ranger.skin.databinding.ActivityMainBinding
import io.github.ranger.skin.databinding.DialogLayoutBinding
import io.github.ranger.skin.databinding.PopupwindowLayoutBinding
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private val popupWindow by lazy {
        PopupWindow(
            PopupwindowLayoutBinding.inflate(layoutInflater).root,
            LayoutParams.WRAP_CONTENT,
            LayoutParams.WRAP_CONTENT
        )
    }
    private val dialog: AlertDialog.Builder
        get() {
            return AlertDialog.Builder(this).apply {
                setView(DialogLayoutBinding.inflate(layoutInflater).root.apply {
                    var flag = false
                    setOnClickListener {
                        if (flag) {
                            SkinManager.instance?.resetSkinStyle()
                        } else {
                            thread {
                                //This operation takes time， It is recommended to load in a child thread
                                SkinManager.instance?.applySkinStyle("${cacheDir.absolutePath}/demo.skin")
                            }
                        }
                        flag = !flag
                    }
                })
                setMessage(R.string.info)
                setFinishOnTouchOutside(true)
            }
        }


    @SuppressLint("InflateParams")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        addTextView()
        initListener()
    }


    private fun initListener() {
        binding.demoView.apply {
            setOnBacClickListener {
                dialog.show()
            }
            setOnMenuClickListener {
                if (popupWindow.isShowing) {
                    popupWindow.dismiss()
                } else {
                    popupWindow.showAsDropDown(
                        it,
                        0,
                        R.dimen.dp_10.getDimension().toInt()
                    )
                }
            }
        }
        binding.resetBtn.setOnClickListener {
            Toast.makeText(this, R.string.reset_skin.getString(), Toast.LENGTH_SHORT).show()
            SkinManager.instance?.resetSkinStyle()

        }
        binding.switchBtn.setOnClickListener {
            Toast.makeText(this, R.string.switch_skin.getString(), Toast.LENGTH_SHORT).show()
            thread {
                //This operation takes time， It is recommended to load in a child thread
                SkinManager.instance?.applySkinStyle("${cacheDir.absolutePath}/demo.skin")
            }
        }
    }

    private fun addTextView() {
        ConstraintLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            0
        ).let {
            it.topMargin = R.dimen.dp_50.getDimension().toInt()
            it.topToBottom = R.id.demo_view
            it.startToStart = R.id.demo_view
            it.endToEnd = R.id.demo_view
            TextView(this).apply {
                layoutParams = it
                //Alternatively,use the View#obtainSingleSkinJob method
                addOnSkinChangeListener {
                    setText2(R.string.extra)
                    setTextSize2(R.dimen.sp_20)
                    setTextColor2(R.color.blue_100)
                }
            }
        }.also {
            binding.root.addView(it)
        }
    }

}