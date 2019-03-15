package com.stackoverflow.utils

import android.content.Context
import android.graphics.Point
import android.util.AttributeSet
import android.view.ViewGroup
import android.view.WindowManager

class CustomViewGroup : ViewGroup {

    constructor(context: Context) : super(context, null, 0)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        val count = childCount
        var curWidth: Int
        var curHeight: Int
        var curLeft: Int
        var curTop: Int
        var maxHeight: Int

        val childLeft = this.paddingLeft
        val childTop = this.paddingTop
        val childRight = this.measuredWidth - this.paddingRight
        val childBottom = this.measuredHeight - this.paddingBottom

        maxHeight = 0
        curLeft = childLeft
        curTop = childTop

        for (i in 0 until count) {
            val child = getChildAt(i)

            val layoutParams = child.layoutParams as ViewGroup.MarginLayoutParams

            if (child.visibility == GONE)
                return

            curWidth = child.measuredWidth + layoutParams.leftMargin + layoutParams.rightMargin
            curHeight = child.measuredHeight

            if (curLeft + curWidth >= childRight) {
                curLeft = childLeft
                curTop += maxHeight + layoutParams.topMargin + layoutParams.bottomMargin
                maxHeight = 0
            }

            child.layout(curLeft, curTop, curLeft + curWidth, curTop + curHeight)

            if (maxHeight < curHeight)
                maxHeight = curHeight
            curLeft += curWidth + layoutParams.leftMargin + layoutParams.rightMargin
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val count = childCount

        val deviceWidth: Int = MeasureSpec.getSize(widthMeasureSpec)

        var maxHeight = 0
        var maxWidth = 0
        var childState = 0
        var mLeftWidth = 0
        var rowCount = 0

        for (i in 0 until count) {
            val child = getChildAt(i)

            val layoutParams = child.layoutParams as ViewGroup.MarginLayoutParams

            if (child.visibility == GONE)
                continue

            measureChild(child, widthMeasureSpec, heightMeasureSpec)
            maxWidth += Math.max(maxWidth, child.measuredWidth)
            mLeftWidth += child.measuredWidth

            if (mLeftWidth / deviceWidth > rowCount) {
                maxHeight += child.measuredHeight + layoutParams.topMargin + layoutParams.bottomMargin
                rowCount++
            } else {
                maxHeight = Math.max(maxHeight, child.measuredHeight)
            }
            childState = combineMeasuredStates(childState, child.measuredState)
        }

        maxHeight = Math.max(maxHeight, suggestedMinimumHeight)
        maxWidth = Math.max(maxWidth, suggestedMinimumWidth)

        setMeasuredDimension(resolveSizeAndState(maxWidth, widthMeasureSpec, childState),
                resolveSizeAndState(maxHeight, heightMeasureSpec, childState shl MEASURED_HEIGHT_STATE_SHIFT))
    }

    override fun generateLayoutParams(attrs: AttributeSet): ViewGroup.LayoutParams {
        return ViewGroup.MarginLayoutParams(context, attrs)
    }

    override fun generateLayoutParams(p: ViewGroup.LayoutParams): ViewGroup.LayoutParams {
        return ViewGroup.MarginLayoutParams(p)
    }

    override fun generateDefaultLayoutParams(): ViewGroup.LayoutParams {
        return ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
    }

    override fun checkLayoutParams(p: ViewGroup.LayoutParams): Boolean {
        return p is ViewGroup.MarginLayoutParams
    }
}