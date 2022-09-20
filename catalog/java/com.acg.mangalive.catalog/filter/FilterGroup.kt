package filter

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.HorizontalScrollView
import android.widget.LinearLayout
import androidx.core.view.children
import com.acg.mangalive.catalog.databinding.FilterGroupBinding

class FilterGroup : HorizontalScrollView {

    private var binding: FilterGroupBinding? = null

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        binding = FilterGroupBinding.inflate(LayoutInflater.from(context), this)

        binding?.resetChip?.setOnClickListener {
            binding?.chipGroup?.children?.forEach {
                (it as? SingleSelectFilter)?.reset()
                (it as? MultipleSelectFilter)?.reset()
            }
        }
    }

    override fun addView(child: View?, index: Int) {
        super.addView(child, index)
    }

    override fun addView(child: View?, params: ViewGroup.LayoutParams?) {
        if (child is LinearLayout) {
            super.addView(child, params)
        } else {
            binding?.chipGroup?.addView(child, params)
        }
    }
}
