package vvar90.freezer.activities

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.Toast
import vvar90.freezer.R
import vvar90.freezer.database.DatabaseHandler

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        val dbHandler = DatabaseHandler(this)
        setOnClickListenerToAddFreezerButton()
        setOnClickListenerToCheckFreezerButton(dbHandler)
    }

    private fun setOnClickListenerToCheckFreezerButton(dbHandler: DatabaseHandler) {

        var checkFreezerButton = findViewById<Button>(R.id.checkFreezersBtn)
            checkFreezerButton.setOnClickListener {
            if (thereAreOneOrMoreFreezers(dbHandler)){
                val openFreezersActivity = Intent(this, FreezersActivity::class.java)
                startActivity(openFreezersActivity)
            }
            else{
                Toast.makeText(this, "No freezers in memory. Please add one", Toast.LENGTH_SHORT)
                        .show()
            }
        }
    }

    private fun thereAreOneOrMoreFreezers(dbHandler: DatabaseHandler): Boolean {
        return dbHandler.freezerDbHandler.getAllFreezers()?.size != 0
                && dbHandler.freezerDbHandler.getAllFreezers() != null
    }
    private fun setOnClickListenerToAddFreezerButton() {

        var addFreezerButton = findViewById<Button>(R.id.addFreezerBtn)

        addFreezerButton.setOnClickListener {
            val openAddFreezerActivity = Intent(this, AddFreezerActivity::class.java)
            startActivity(openAddFreezerActivity)
        }
    }

}
