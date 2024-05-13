# My Notes Application


## Description
In this project, I aimed to develop a note-taking application that allows me to keep track of my notes locally. With this application, you can save your notes, and when you reopen the application, the saved notes will be displayed in a list retrieved from the SQLite database of the application. You can also delete any selected note whenever you want.

## Features
- Creating and managing an SQLite database for storing and deleting data.
- Understanding the Android Activity Lifecycle and updating the data (the list of notes) when returning to a previously entered activity using the onResume method.
- Setting up and configuring a DatePicker Dialog for selecting dates.
- Utilizing an AlertDialog for displaying important messages or prompts.

## Technologies Used
- Kotlin
- Android Studio
- SQLite Database
- DatePicker Dialog
- AlertDialog

## Demo
- 1) Adding Note
- 2) Note Detail and Deleting Note

### 1) Adding Note

- You can add the title, details, and the corresponding date of the note in the screen below. By using the DatePicker Dialog, select the desired date and click the "Save Note" button to add it to the database. When you revisit this activity in the application, the note will be displayed in the list.

<p align="center">
  <img src="https://github.com/aknemreyzc/my_notes_app/assets/116732291/78527424-8c62-4649-813b-6823206fb705" width="500" style="display: block; margin: auto;">
</p>

### 2) Note Detail and Deleting Note

- The following screen aims to provide the functionality of navigating to the detail page by clicking on a note in the list. On the detail page, the relevant information will be displayed. The user will have the option to delete the note, which requires a double confirmation using an AlertDialog. After deleting the note, the user will be returned to the previous screen, and the list will be updated accordingly.

<p align="center">
  <img src="https://github.com/aknemreyzc/my_notes_app/assets/116732291/fa36f2d4-b398-48a2-9aa0-a58b9205302e" width="500" style="display: block; margin: auto;">
</p>





