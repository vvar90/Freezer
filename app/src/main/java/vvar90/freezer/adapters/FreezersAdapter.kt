package vvar90.freezer.adapters

import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.Adapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import vvar90.freezer.R
import vvar90.freezer.dao.Freezer

/**
 * Created by vvar9 on 08/12/2017.
 */
class FreezersAdapter(freezers: List<Freezer>) : Adapter<FreezersAdapter.FreezersViewHolder>() {

    private var freezerList: List<Freezer> = freezers

    class FreezersViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        var deleteButton: ImageButton = itemView?.findViewById(R.id.deleteFreezerBtn)!!
        var freezerName: TextView = itemView?.findViewById(R.id.itemText)!!
        var snowFlakePic: ImageView = itemView?.findViewById(R.id.snowFlakeImage)!!
    }

    override fun onBindViewHolder(holder: FreezersViewHolder, position: Int) {
        var freezer = freezerList[position]
        holder.freezerName.text = freezer.name
    }

    override fun getItemCount(): Int {
        return freezerList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): FreezersViewHolder {
        val itemView = LayoutInflater.from(parent?.context).inflate(R.layout.list_row,
                parent, false)
        return FreezersViewHolder(itemView)
    }
}

