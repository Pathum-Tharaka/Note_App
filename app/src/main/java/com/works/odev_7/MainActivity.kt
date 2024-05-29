package com.works.odev_7

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import com.works.odev_7.adapter.NoteListAdapter
import com.works.odev_7.models.Notes
import java.util.Calendar

// MainActivity class for the main screen of the application
class MainActivity : AppCompatActivity() {

    // UI elements declaration
    lateinit var txtTitle: EditText
    lateinit var txtDetail: EditText
    lateinit var btnDate: Button
    lateinit var btnSave: Button
    lateinit var listNotes : ListView

    lateinit var db: DB

    var selectedDate = ""

    // Function called when the activity is created
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize UI elements
        txtTitle = findViewById(R.id.txtTitle)
        txtDetail = findViewById(R.id.txtDetail)
        btnDate = findViewById(R.id.btnDate)
        btnSave = findViewById(R.id.btnSave)
        listNotes = findViewById(R.id.listNotes)

        // Initialize database handler
        db = DB(this)

        // Initialize calendar for getting current date
        val calendar = Calendar.getInstance()

        // OnClickListener for the date button to open a DatePickerDialog
        btnDate.setOnClickListener {
            val datePickerDialog = DatePickerDialog(
                this,
                DatePickerDialog.OnDateSetListener { datePicker, i, i2, i3 ->
                    var ay = "${i2 + 1}"
                    if (i2 + 1 < 10) {
                        ay = "0" + ay
                    }
                    selectedDate = "$i3.$ay.$i"
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            )
            datePickerDialog.show()
        }

        // OnClickListener for the save button to add a new note
        btnSave.setOnClickListener {
            val title = txtTitle.text.toString()
            val detail = txtDetail.text.toString()
            if (selectedDate != "" && title != "" && detail != "") {
                val status = db.addNote(title, detail, selectedDate)
                val allNotes = db.showNotes()
            } else {
                Toast.makeText(this,"Lütfen alanları eksiksiz doldurdun",Toast.LENGTH_LONG).show()
            }
        }

        // Initialize custom adapter for the ListView
        val customAdapter = NoteListAdapter(this, db.showNotes())
        listNotes.adapter = customAdapter

        // Set item click listener for the ListView to view note details
        listNotes.setOnItemClickListener { adapterView, view, i, l ->
            val selectedNote = listNotes.getItemAtPosition(i) as Notes
            val intent = Intent(this, NoteDetailActivity::class.java)
            intent.putExtra("Title", selectedNote.Title)
            intent.putExtra("Detail", selectedNote.Detail)
            intent.putExtra("Date", selectedNote.Date)
            intent.putExtra("NID", selectedNote.NID)
            startActivity(intent)
        }
    }

    // Function called when the activity is resumed
    override fun onResume() {
        super.onResume()
        // Refresh the ListView with updated notes
        val customNewAdapter = NoteListAdapter(this, db.showNotes())
        listNotes.adapter = customNewAdapter
    }
}
