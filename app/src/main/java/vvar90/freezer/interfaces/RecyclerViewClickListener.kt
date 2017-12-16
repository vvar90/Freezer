package vvar90.freezer.interfaces

import android.view.View

/**
 * Created by vvar9 on 16/12/2017.
 */
interface RecyclerViewClickListener {

    fun onClick(view: View, position: Int)

    fun onLongClick(view: View, position: Int)
}