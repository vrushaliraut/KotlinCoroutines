package com.examples.kotlincoroutines

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class ItemOffsetDecoration(weatherAppActivity: WeatherAppActivity, private val itemOffset: Int) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView,
                                state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect.set(
                itemOffset,
                itemOffset,
                itemOffset,
                itemOffset
        )
    }

}
