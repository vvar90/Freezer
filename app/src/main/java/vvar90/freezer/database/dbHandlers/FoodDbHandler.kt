package vvar90.freezer.database.dbHandlers

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import vvar90.freezer.dao.Food

/**
 * Created by vvar9 on 08/12/2017.
 */
class FoodDbHandler(context: Context, dbName: String, dbVersion: Int)
    : SQLiteOpenHelper(context, dbName, null, dbVersion){

    companion object {
        val TABLE_FOOD = "food"
        val KEY_FREEZER = "freezer"
        val KEY_ADDED_DATE = "addedDate"
        val KEY_DUE_DATE = "dueDate"
        val KEY_FOOD_NAME = "name"
        val KEY_DESCRIPTION = "description"
    }

    override fun onCreate(sqLiteDatabase: SQLiteDatabase) {
        val CREATE_FOOD_TABLE = "CREATE TABLE " + TABLE_FOOD +
                "(" +
                KEY_FREEZER + " FREEZER NAME" +
                KEY_ADDED_DATE + " ADDED DATE" +
                KEY_DUE_DATE + " DUE DATE" +
                KEY_FOOD_NAME + " FOOD NAME" +
                KEY_DESCRIPTION + " DESCRIPTION" +
                ")"

        sqLiteDatabase.execSQL(CREATE_FOOD_TABLE)
    }

    override fun onUpgrade(sqLiteDatabase: SQLiteDatabase, i: Int, i1: Int) {
        // Drop older freezer table if it exists
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_FOOD)

        // call onCreate method so it creates new table again
        onCreate(sqLiteDatabase)
    }

    /**
     * CRUD Operations (create, Read, Update, Delete)
     */

    fun addFood(food: Food) {
        val db = this.writableDatabase

        val values = ContentValues()
        values.put(KEY_FREEZER, food.freezer)
        values.put(KEY_ADDED_DATE, food.addedDate)
        values.put(KEY_DUE_DATE, food.dueDate)
        values.put(KEY_FOOD_NAME, food.name)
        values.put(KEY_DESCRIPTION, food.description)

        db.insert(TABLE_FOOD, null, values)
        db.close() // Closing database connection
    }

    //TODO: Filter also for freezerName. Rename to getFoodGivenFreezer or something like that
    fun getFood(foodName: String, freezerName: String): Food? {
        val db = this.readableDatabase
        var food: Food? = null

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        val projection = arrayOf(KEY_FOOD_NAME, KEY_FREEZER)

        // Filter results WHERE "column_name" = 'value'
        // here selection is column_name and
        // selectionArgs is value
        val selection = KEY_FOOD_NAME + "=?"
        val selectionArgs = arrayOf(foodName)

        // The order in which your result needs to be returned
        val sortOrder: String? = null // pass null if don't want it to be sorted

        // If passed 5, only 5 freezers will be returned
        val limit: String? = null // pass null if you dont want it to limit

        val cursor = db.query(
                TABLE_FOOD,        // The table to query
                projection,         // The columns to return
                selection,          // The columns for WHERE clause
                selectionArgs,      // The values for WHERE clause;
                null,        // don't group the rows
                null,         // don't filter
                sortOrder,          // The sort Order
                limit               // don't limit
        )

        cursor.let {
            cursor.moveToFirst()
            food = Food(
                    cursor.getString(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4)
            )
        }

        return food
    }

    fun getAllFreezerFood(freezer: String): List<Food>? {

        val db = this.readableDatabase

        val selectQuery = "SELECT  * FROM " + TABLE_FOOD + "WHERE freezer =?" + freezer


        val cursor = db.rawQuery(selectQuery, null)

        cursor.let {
            if (cursor.moveToFirst()) {
                var foodList = arrayListOf<Food>()
                do {
                    val food = Food(
                            cursor.getString(0),
                            cursor.getString(1),
                            cursor.getString(2),
                            cursor.getString(3),
                            cursor.getString(4)
                    )
                    // Adding Freezer to list
                    foodList.add(food)
                } while (cursor.moveToNext())

                cursor.close()
                db.close()
                // return freezerList if there is freezers in database
                return foodList
            }
        }
        //return null if the cursor was null
        return null
    }

    fun updateFood(food: Food): Int {
        val db = writableDatabase

        // New values
        val values = ContentValues()
        values.put(KEY_FREEZER, food.freezer)
        values.put(KEY_ADDED_DATE, food.addedDate)
        values.put(KEY_DUE_DATE, food.dueDate)
        values.put(KEY_FOOD_NAME, food.name)
        values.put(KEY_DESCRIPTION, food.description)

        // updating row
        return db.update(TABLE_FOOD, values, KEY_FOOD_NAME + " = ?",
                arrayOf(food.name))
    }

    //TODO: Filter also by freezer name
    fun deleteFoodForAGivenFreezer(food: Food, freezer: String) {
        val db = writableDatabase
        db.delete(
                TABLE_FOOD, // table name
                KEY_FOOD_NAME + " = ?", // selection
                arrayOf(food.name) // selectionArgs
        )
        db.close()
    }

    fun getFoodCountGivenFreezer(freezer: String): Int {
        val db = writableDatabase

        val countQuery = "SELECT  * FROM " + TABLE_FOOD + "WHERE freezer =?" + freezer

        val cursor = db.rawQuery(countQuery, null)
        val count = cursor.count

        cursor.close()
        db.close()

        return count
    }
}