package vvar90.freezer.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import vvar90.freezer.adapters.FreezersAdapter
import vvar90.freezer.R

class FreezersActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_freezers)

        val listView = findViewById<ListView>(R.id.listViewItem)
        val freezerList = dbHandler.freezerDbHandler.getAllFreezers()!!.map { freezer -> freezer.name }
        listView.adapter = FreezersAdapter(this, freezerList)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }
}
