package vvar90.freezer.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import vvar90.freezer.R

/**
 * Created by vvar9 on 12/12/2017.
 */
class FoodAdapter(context: Context, food: List<String>) : BaseAdapter()  {
    private val mInflator: LayoutInflater = LayoutInflater.from(context)
    private var foodList: List<String> = food

    override fun getItem(position: Int): Any {
        return foodList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return foodList.size
    }

    override fun getView(position1: Int, convertView: View?, parent: ViewGroup?): View? {
        val view: View?
        val vh: ListRowHolder
        if (convertView == null) {
            view = this.mInflator.inflate(R.layout.list_row, parent, false)
            vh = ListRowHolder(view)
            view?.tag = vh
        } else {
            view = convertView
            vh = view.tag as ListRowHolder
        }

        vh.label.text = foodList[position1].toString()
        return view
    }
    private class ListRowHolder(row: View?) {
        public val label: TextView = row?.findViewById<TextView>(R.id.label) as TextView

    }
}