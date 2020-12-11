package com.example.notetoselfbatuhanbaran

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.DialogFragment


class DialogShowNote : DialogFragment(){

    private var myNote = Note()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val builder = AlertDialog.Builder(activity!!)

        val inflater = activity!!.layoutInflater

        val myDialogView = inflater.inflate(R.layout.dialog_show_note, null)

        val titleTxt = myDialogView.findViewById<TextView>(R.id.titleTxtView)
        val descTxt = myDialogView.findViewById<TextView>(R.id.descTxtView)

        val todoTxt = myDialogView.findViewById<TextView>(R.id.todoTxtView)
        val importantTxt = myDialogView.findViewById<TextView>(R.id.importantTxtView)
        val ideaTxt = myDialogView.findViewById<TextView>(R.id.ideaTxtView)


        val doneButton = myDialogView.findViewById<Button>(R.id.done_button)

        titleTxt.text = myNote.title
        descTxt.text = myNote.description

        if (!myNote!!.important){
            importantTxt.visibility = View.GONE
        }

        if (!myNote!!.todo){
            todoTxt.visibility = View.GONE
        }

        if (!myNote!!.idea){
            ideaTxt.visibility = View.GONE
        }

        doneButton.setOnClickListener {

            dismiss()
        }


        builder.setView(myDialogView).setMessage(resources.getString(
                R.string.your_note))

        return builder.create()


    }

    fun sendNoteSelected(noteSelected: Note) {
        myNote = noteSelected
    }

}