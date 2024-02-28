package com.rangerleo.skin.demo

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.rangerleo.skin.core.SkinViewSupport
import com.rangerleo.skin.ktx.getColorStateList
import com.rangerleo.skin.ktx.setText2
import io.github.ranger.skin.R
import io.github.ranger.skin.databinding.HeaderBinding

class DemoView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr), SkinViewSupport {

    private val viewBinding by lazy {
        HeaderBinding.inflate(LayoutInflater.from(context), this, true)
    }

    fun setOnBacClickListener(onClickListener: OnClickListener) {
        viewBinding.back.setOnClickListener(onClickListener)
    }

    fun setOnMenuClickListener(onClickListener: OnClickListener) {
        viewBinding.menu.setOnClickListener(onClickListener)
    }

    override fun applySkin() {
        viewBinding.apply {
            title.setText2(R.string.title)
            R.color.black.getColorStateList()?.also {
                back.drawable?.setTintList(it)
                menu.drawable?.setTintList(it)
            }
        }
    }
}