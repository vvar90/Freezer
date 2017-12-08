package vvar90.freezer.Activities

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import vvar90.freezer.Data.Freezer
import vvar90.freezer.Database.DatabaseHandler
import vvar90.freezer.R

class AddFreezerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_freezer)

        val dbHandler = DatabaseHandler(this)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        setOnClickListenerToAddFreezerButton(dbHandler)
    }

    @SuppressLint("WrongViewCast")
    private fun setOnClickListenerToAddFreezerButton(dbHandler: DatabaseHandler) {

        val addFreezerButton = findViewById<Button>(R.id.addFreezer)
        val numberOfFreezers = dbHandler.getFreezerCount()
        addFreezerButton.setOnClickListener {
            var freezerName = findViewById<EditText>(R.id.freezerNameEditText).text.toString()
            if(isNameBlank(freezerName)){
                Toast.makeText(this, "Name cannot be empty", Toast.LENGTH_SHORT)
                        .show()
            }
            else{
                addFreezerAndFinishActivity(freezerName, numberOfFreezers, dbHandler)
            }
        }
    }

    private fun addFreezerAndFinishActivity(freezerName: String, numberOfFreezers: Int, dbHandler: DatabaseHandler) {
        val freezer = Freezer(freezerName, numberOfFreezers + 1)
        dbHandler.addFreezer(freezer)
        Toast.makeText(this, "Freezer $freezerName successfully added", Toast.LENGTH_SHORT)
                .show()
        finish()
    }

    private fun isNameBlank(freezerName: String): Boolean {
        return freezerName.isNullOrEmpty()
    }
}
