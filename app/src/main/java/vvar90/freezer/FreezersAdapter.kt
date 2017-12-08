package vvar90.freezer

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import vvar90.freezer.Data.Freezer
import vvar90.freezer.Database.DatabaseHandler

/**
 * Created by vvar9 on 08/12/2017.
 */
class FreezersAdapter(context: Context) : BaseAdapter() {

    private val mInflator: LayoutInflater = LayoutInflater.from(context)
    private var freezerNames: List<Freezer>
    private val dbHandler: DatabaseHandler = DatabaseHandler(context)

    init {
        freezerNames = dbHandler.getAllFreezers()!!
    }
    override fun getItem(position: Int): Any {
        return freezerNames[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return freezerNames.size
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

        vh.label.text = freezerNames[position1].toString()
        return view
    }
    private class ListRowHolder(row: View?) {
        public val label: TextView

        init {
            this.label = row?.findViewById<TextView>(R.id.label) as TextView
        }
    }
}

