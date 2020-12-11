package com.example.notetoselfbatuhanbaran

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import androidx.fragment.app.DialogFragment

class DialogNewNote : DialogFragment(){

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {


        val builder = AlertDialog.Builder(activity!!)

        val inflater = activity!!.layoutInflater

        val myDialogView = inflater.inflate(R.layout.dialog_new_note, null)

        val editTitle = myDialogView.findViewById(R.id.editTitle) as EditText
        val editDesc = myDialogView.findViewById(R.id.editDescription) as EditText

        //Our checboxes

        val cBoxIdea = myDialogView.findViewById<CheckBox>(R.id.ideaCheckBox)
        val cBoxTodo = myDialogView.findViewById<CheckBox>(R.id.todoCheckBox)
        val cBoxImportant = myDialogView.findViewById<CheckBox>(R.id.importantCheckBox)

        //Our buttons

        val cancelButton = myDialogView.findViewById<Button>(R.id.cancelButton)

        val okButton = myDialogView.findViewById<Button>(R.id.okButton)


        //Now we are set our new view from builder that we created

        builder.setView(myDialogView).setMessage(resources.getString(
                R.string.add_new_note))


        cancelButton.setOnClickListener{

            dismiss()   //dismiss current view
        }

        okButton.setOnClickListener {

            // new note

            val newNote = Note()

            newNote.title = editTitle.text.toString()

            newNote.description = editDesc.text.toString()
            newNote.idea = cBoxIdea.isChecked
            newNote.todo = cBoxTodo.isChecked
            newNote.important = cBoxImportant.isChecked


            val callingActivity = activity as MainActivity? // reference from MainActivity

            callingActivity!!.createNewNote(newNote)
            dismiss()

        }

        return builder.create()
    }




}