package vvar90.freezer.Activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import vvar90.freezer.FreezersAdapter
import vvar90.freezer.R

class FreezersActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_freezers)

        val listView = findViewById<ListView>(R.id.listViewItem)
        listView.adapter = FreezersAdapter(this)
    }
}
