package com.works.odev_7

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.works.odev_7.models.Notes

// DB class for managing notes SQLite database operations
class DB(context: Context) : SQLiteOpenHelper(context, DBName, null, version) {

    companion object {
        // Companion object containing constants for database name and version
        private val DBName = "note.db"
        private val version = 1
    }

    // Function called when the database is created for the first time
    override fun onCreate(db: SQLiteDatabase?) {
        // SQL query to create the "notes" table
        val noteTable = "CREATE TABLE \"notes\" (\n" +
                "\t\"NID\"\tINTEGER,\n" +
                "\t\"Title\"\tTEXT,\n" +
                "\t\"Detail\"\tTEXT,\n" +
                "\t\"Date\"\tTEXT,\n" +
                "\tPRIMARY KEY(\"NID\" AUTOINCREMENT)\n" +
                ");"
        // Execute the SQL query to create the table
        db?.execSQL(noteTable)
    }

    // Function called when the database needs to be upgraded
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        // SQL query to drop the "notes" table if it exists
        val noteTable = "DROP TABLE IF EXISTS notes"
        // Execute the SQL query to drop the table
        db?.execSQL(noteTable)
        // Recreate the database table by calling onCreate method
        onCreate(db)
    }

    // Function to add a new note to the database
    fun addNote(title: String, detail: String, date: String): Long {
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put("Title", title)
            put("Detail", detail)
            put("Date", date)
        }
        // Insert the values into the "notes" table and return the ID of the newly inserted row
        val affectedRow = db.insert("notes", null, values)
        db.close()
        return affectedRow
    }

    // Function to retrieve all notes from the database
    fun showNotes(): List<Notes> {
        val arr = mutableListOf<Notes>()
        val db = this.readableDatabase
        // Query to select all records from the "notes" table
        val cursor = db.query("notes", null, null, null, null, null, null)
        // Loop through the cursor and populate the list with Notes objects
        while (cursor.moveToNext()) {
            val nID = cursor.getInt(0)
            val title = cursor.getString(1)
            val detail = cursor.getString(2)
            val date = cursor.getString(3)
            val note = Notes(nID, title, detail, date)
            arr.add(note)
        }
        db.close()
        return arr
    }

    // Function to delete a note from the database based on its ID
    fun deleteNote(nID: Int): Int {
        val db = this.writableDatabase
        // Delete the note with the given ID from the "notes" table and return the number of affected rows
        val status = db.delete("notes", "NID = $nID", null)
        db.close()
        return status
    }
}
