package com.works.odev_7.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.works.odev_7.R
import com.works.odev_7.models.Notes

// NoteListAdapter class for custom ArrayAdapter to display notes in a list
class NoteListAdapter(
    private val context: Activity,
    private val list: List<Notes>
) : ArrayAdapter<Notes>(context, R.layout.notes_list, list) {

    // Function to get a View that displays the data at the specified position in the data set
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        // Inflate the layout for each row
        val rootView = LayoutInflater.from(context).inflate(R.layout.notes_list, parent, false)

        // Get the TextView for displaying note title
        val rTitle = rootView.findViewById<TextView>(R.id.r_title)

        // Get the note object at the specified position in the data set
        val note = list[position]

        // Set the text of TextView to the note title
        rTitle.text = note.Title

        return rootView
    }
}
