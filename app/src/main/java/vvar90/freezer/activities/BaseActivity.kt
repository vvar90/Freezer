package vvar90.freezer.activities

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import vvar90.freezer.R

import kotlinx.android.synthetic.main.activity_base.*
import vvar90.freezer.database.DatabaseHandler

open class BaseActivity : AppCompatActivity() {

    lateinit var dbHandler: DatabaseHandler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dbHandler = DatabaseHandler(this)
    }
}
