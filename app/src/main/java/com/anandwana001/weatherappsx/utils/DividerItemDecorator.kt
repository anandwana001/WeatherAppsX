package com.anandwana001.weatherappsx.utils

import android.graphics.Canvas
import android.graphics.drawable.Drawable
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by anandwana001 on
 * 06, February, 2022
 **/
class DividerItemDecorator(private val mDivider: Drawable) : RecyclerView.ItemDecoration() {

  override fun onDraw(canvas: Canvas, parent: RecyclerView, state: RecyclerView.State) {
    val dividerLeft = parent.paddingLeft
    val dividerRight = parent.width - parent.paddingRight

    val childCount = parent.childCount
    for (i in 0 until childCount - 1) {
      val child = parent.getChildAt(i)

      val params = child.layoutParams as RecyclerView.LayoutParams

      val dividerTop = child.bottom + params.bottomMargin
      val dividerBottom = dividerTop + mDivider.intrinsicHeight

      mDivider.setBounds(dividerLeft, dividerTop, dividerRight, dividerBottom)
      mDivider.draw(canvas)
    }
  }
}