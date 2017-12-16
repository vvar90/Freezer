package vvar90.freezer.helpers

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.GestureDetector
import android.view.MotionEvent
import vvar90.freezer.interfaces.RecyclerViewClickListener

/**
 * Created by vvar9 on 16/12/2017.
 */
class RecyclerTouchListener(var context: Context, var recyclerView: RecyclerView, private var recyclerViewClickListener: RecyclerViewClickListener) : RecyclerView.OnItemTouchListener {

    val gestureDetector = GestureDetector(context, GestureDetector.SimpleOnGestureListener())

    override fun onInterceptTouchEvent(rv: RecyclerView?, e: MotionEvent?): Boolean {
        var child = rv!!.findChildViewUnder(e!!.x, e.y)
        if (child != null && gestureDetector.onTouchEvent(e)){
            recyclerViewClickListener.onClick(child, rv.getChildAdapterPosition(child))
        }
        return false
    }

    override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onTouchEvent(rv: RecyclerView?, e: MotionEvent?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}