package com.works.odev_7

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog

// NoteDetailActivity class for displaying details of a specific note
class NoteDetailActivity : AppCompatActivity() {

    // UI elements declaration
    lateinit var btnDelete: Button
    lateinit var txtDetailTitle: TextView
    lateinit var txtDetailDetail: TextView
    lateinit var txtDetailDate: TextView
    lateinit var db: DB

    // Function called when the activity is created
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_detail)

        // Initialize UI elements
        btnDelete = findViewById(R.id.btnDelete)
        txtDetailTitle = findViewById(R.id.txtDetailTitle)
        txtDetailDetail = findViewById(R.id.txtDetailDetail)
        txtDetailDate = findViewById(R.id.txtDetailDate)

        // Initialize database handler
        db = DB(applicationContext)

        // Set text of TextViews with note details received from intent
        txtDetailTitle.text = intent.getStringExtra("Title")
        txtDetailDetail.text = "  " + intent.getStringExtra("Detail")
        txtDetailDate.text = "Date : " + intent.getStringExtra("Date") + "     "
        val NID = intent.getIntExtra("NID",0)

        // OnClickListener for the delete button to delete the note
        btnDelete.setOnClickListener {
            val alert = AlertDialog.Builder(this)
            alert.setMessage("Are you sure you want to delete this note?")
            alert.setCancelable(false)
            alert.setNegativeButton("Cancel", DialogInterface.OnClickListener { dialogInterface, i ->
                Log.d("Cancel", "Deletion canceled")
            })
            alert.setPositiveButton("Yes", DialogInterface.OnClickListener { dialogInterface, i ->
                val status = db.deleteNote(NID)
                Log.d("status", status.toString())
                finish()
            })
            alert.show()
        }
    }
}
