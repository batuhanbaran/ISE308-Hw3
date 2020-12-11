package com.example.notetoselfbatuhanbaran

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class NoteAdapter(

    private val mainActivity: MainActivity,
    private val noteList: List<Note>):
    RecyclerView.Adapter<NoteAdapter.ListItemHolder>() {

    inner class ListItemHolder(view: View) :
        RecyclerView.ViewHolder(view),
        View.OnClickListener {

        internal var title =
            view.findViewById<View>(
                R.id.textView2) as TextView
        internal var description =
            view.findViewById<View>(
                R.id.textView3) as TextView
        internal var status =
            view.findViewById<View>(
                R.id.textView1) as TextView
        init {
            view.isClickable = true
            view.setOnClickListener(this)
        }
        override fun onClick(view: View) {

            mainActivity.showNote(adapterPosition)

        }
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListItemHolder {

        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.main_list, parent, false)

        return ListItemHolder(itemView)
    }

    override fun getItemCount(): Int {
        if (noteList != null) {
            return noteList.size
        }
        // error
        return -1
    }

    override fun onBindViewHolder(
        holder: ListItemHolder, position: Int) {
        val note = noteList[position]
        holder.title.text = note.title

        // Show limited characters of the actual note

        holder.description.text =
            note.description!!.substring(0, 15)

        when {

            note.idea -> holder.status.text =
                mainActivity.resources.getString(R.string.idea_text)
            note.important -> holder.status.text =
                mainActivity.resources.getString(R.string.important_text)
            note.todo -> holder.status.text =
                mainActivity.resources.getString(R.string.todo_text)
        }
    }

}








