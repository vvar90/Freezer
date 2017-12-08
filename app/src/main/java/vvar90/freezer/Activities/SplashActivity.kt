package vvar90.freezer.Activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import vvar90.freezer.Database.DatabaseHandler
import vvar90.freezer.R

class SplashActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        val dbHandler = DatabaseHandler(this)
        setOnClickListenerToAddFreezerButton(dbHandler)
        setOnClickListenerToCheckFreezerButton(dbHandler)
    }

    private fun setOnClickListenerToCheckFreezerButton(dbHandler: DatabaseHandler) {

        var checkFreezerButton = findViewById<Button>(R.id.checkFreezersBtn)

        checkFreezerButton.setOnClickListener {
            val openFreezersActivity = Intent(this, FreezersActivity::class.java)
            startActivity(openFreezersActivity)
        }
    }

    private fun setOnClickListenerToAddFreezerButton(dbHandler: DatabaseHandler) {

        var addFreezerButton = findViewById<Button>(R.id.addFreezerBtn)

        addFreezerButton.setOnClickListener {
            val openAddFreezerActivity = Intent(this, AddFreezerActivity::class.java)
            startActivity(openAddFreezerActivity)
        }
    }

}
