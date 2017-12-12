package vvar90.freezer.activities

import android.os.Bundle
import android.widget.ListView
import vvar90.freezer.R
import vvar90.freezer.adapters.FoodAdapter
import vvar90.freezer.dao.Food

class FreezerFoodActivity : BaseActivity() {

    private lateinit var mFreezer: String
    private lateinit var mFood: List<Food>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mFreezer = intent.getStringExtra("freezer").toString()
        mFood = dbHandler.foodDbHandler.getAllFreezerFood(mFreezer)!!
        startActivity()
        setContentView(R.layout.activity_freezer_food)
    }

    private fun startActivity(){
        var foodList = mFood.map { food -> food.name }
        val listView = findViewById<ListView>(R.id.listViewItemFood)
        listView.adapter = FoodAdapter(this, foodList)
    }
}
