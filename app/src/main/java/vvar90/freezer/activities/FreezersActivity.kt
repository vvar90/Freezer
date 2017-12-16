package vvar90.freezer.activities

import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import vvar90.freezer.R
import vvar90.freezer.adapters.FreezersAdapter
import vvar90.freezer.dao.Freezer

class FreezersActivity : BaseActivity() {

    private lateinit var freezerList: List<Freezer>
    private lateinit var freezersAdapter: FreezersAdapter
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_freezers)

        init()
        setupAdapter()
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    private fun init() {
        freezerList = dbHandler.freezerDbHandler.getAllFreezers()!!
        recyclerView = findViewById(R.id.listViewItem)
        freezersAdapter = FreezersAdapter(freezerList)
    }

    private fun setupAdapter(){
        var layoutManager = LinearLayoutManager(applicationContext)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = freezersAdapter
        recyclerView.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))
    }
    fun onItemClick(){
        //TODO
    }
}
