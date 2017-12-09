package vvar90.freezer.database

import android.content.Context
import vvar90.freezer.database.dbHandlers.FoodDbHandler
import vvar90.freezer.database.dbHandlers.FreezerDbHandler

/**
 * Created by vvar9 on 07/12/2017.
 */
class DatabaseHandler(context: Context){

    // Kotlin does not allow static variables or functions
    companion object {
        val DATABASE_NAME = "freezer_db"

        val DATABASE_VERSION = 1
    }

    val freezerDbHandler: FreezerDbHandler = FreezerDbHandler(context, DATABASE_NAME,
            DATABASE_VERSION)
    val foodDbHandler : FoodDbHandler = FoodDbHandler(context, DATABASE_NAME, DATABASE_VERSION)
}