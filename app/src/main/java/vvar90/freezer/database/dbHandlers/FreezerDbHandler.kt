package vvar90.freezer.database.dbHandlers

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import vvar90.freezer.dao.Freezer

/**
 * Created by vvar9 on 08/12/2017.
 */
class FreezerDbHandler(context: Context, dbName: String, dbVersion: Int)
    : SQLiteOpenHelper(context, dbName, null, dbVersion){

    companion object {
        val TABLE_FREEZER = "freezers"
        val KEY_NAME = "name"
    }

    override fun onCreate(sqLiteDatabase: SQLiteDatabase) {
        val CREATE_FREEZER_TABLE = "CREATE TABLE " + TABLE_FREEZER +
                "(" +
                KEY_NAME + " NAME" +
                ")"

        sqLiteDatabase.execSQL(CREATE_FREEZER_TABLE)
    }

    override fun onUpgrade(sqLiteDatabase: SQLiteDatabase, i: Int, i1: Int) {
        // Drop older freezer table if it exists
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_FREEZER)

        // call onCreate method so it creates new table again
        onCreate(sqLiteDatabase)
    }

    /**
     * CRUD Operations (create, Read, Update, Delete)
     */

    fun addFreezer(freezer: Freezer) {
        // Get writable database
        val db = this.writableDatabase

        val values = ContentValues()
        values.put(KEY_NAME, freezer.name) // Freezer name

        db.insert(TABLE_FREEZER, null, values)
        db.close() // Closing database connection
    }

    // Get Single Freezer
    fun getFreezer(freezerName: String): Freezer? {
        val db = this.readableDatabase
        var freezer: Freezer? = null

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        val projection = arrayOf(KEY_NAME)


        // Filter results WHERE "column_name" = 'value'
        // here selection is column_name and
        // selectionArgs is value
        val selection = KEY_NAME + "=?"
        val selectionArgs = arrayOf(freezerName)

        // The order in which your result needs to be returned
        val sortOrder: String? = null // pass null if don't want it to be sorted

        // If passed 5, only 5 freezers will be returned
        val limit: String? = null // pass null if you dont want it to limit

        val cursor = db.query(
                TABLE_FREEZER,        // The table to query
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
            freezer = Freezer(
                    cursor.getString(0)
            )
        }

        // return freezer
        return freezer
    }

    // Get All Freezers
    fun getAllFreezers(): List<Freezer>? {

        // here this refers to SQLiteDatabase
        val db = this.readableDatabase

        // SQL query for getting all records from the database
        val selectQuery = "SELECT  * FROM " + TABLE_FREEZER


        val cursor = db.rawQuery(selectQuery, null)

        cursor.let {
            if (cursor.moveToFirst()) {
                var freezerList = arrayListOf<Freezer>()
                do {
                    var freezer = Freezer(
                            cursor.getString(0)
                    )
                    // Adding Freezer to list
                    freezerList.add(freezer)
                } while (cursor.moveToNext())

                cursor.close()
                db.close()
                // return freezerList if there is freezers in database
                return freezerList
            }
        }
        //return null if the cursor was null
        return null
    }

    // Updating single freezer
    fun updateFreezer(freezer: Freezer): Int {
        val db = writableDatabase

        // New values
        val values = ContentValues()
        values.put(KEY_NAME, freezer.name)

        // updating row
        return db.update(TABLE_FREEZER, values, KEY_NAME + " = ?",
                arrayOf(freezer.name))
    }

    // Deleting single Freezer
    fun deleteFreezer(freezer: Freezer) {
        val db = writableDatabase
        db.delete(
                TABLE_FREEZER, // table name
                KEY_NAME + " = ?", // selection
                arrayOf(freezer.name) // selectionArgs
        )
        db.close()
    }

    // Getting Freezer Count
    fun getFreezerCount(): Int {
        val db = writableDatabase

        val countQuery = "SELECT  * FROM " + TABLE_FREEZER

        val cursor = db.rawQuery(countQuery, null)
        val count = cursor.count

        cursor.close()
        db.close()

        // return count
        return count
    }
}