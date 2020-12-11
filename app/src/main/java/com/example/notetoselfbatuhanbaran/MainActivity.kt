package com.example.notetoselfbatuhanbaran

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private var note = Note()
    private var mSerializer: JSONSerializer? = null
    private var noteList: ArrayList<Note>? = null

    private var recyclerView: RecyclerView? = null
    private var adapter: NoteAdapter? = null
    private var showDividers: Boolean = false


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        imageView.setOnClickListener { view ->

            val dialog = DialogNewNote()
            dialog.show(supportFragmentManager, "123")

        }
        mSerializer = JSONSerializer("NoteToSelf.json",
            applicationContext)
        try {
            noteList = mSerializer!!.load()
        } catch (e: Exception) {
            noteList = ArrayList()
            Log.e("Error loading notes: ", "", e)
        }


        recyclerView = findViewById<View>(R.id.recyclerView) as RecyclerView
        adapter = NoteAdapter(this, this.noteList!!)
        val layoutManager = LinearLayoutManager(applicationContext)
        recyclerView!!.layoutManager = layoutManager

        recyclerView!!.itemAnimator = DefaultItemAnimator()


        // set the adapter
        recyclerView!!.adapter = adapter
    }

    fun createNewNote(myNote: Note){

        noteList!!.add(myNote)
        adapter!!.notifyDataSetChanged()

    }

    fun showNote(noteToShow: Int) {
        val dialog = DialogShowNote()
        dialog.sendNoteSelected(noteList!![noteToShow])
        dialog.show(supportFragmentManager, "")
    }



    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        val inflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)

        return super.onCreateOptionsMenu(menu)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when (item.itemId) {
            R.id.settingsItem -> {
                val intent = Intent(this,
                        SettingsActivity::class.java)
                startActivity(intent)
                true }
            else -> super.onOptionsItemSelected(item)
        }

    }

    override fun onResume() {
        super.onResume()

        val prefs = getSharedPreferences(
                "Note to self",
                Context.MODE_PRIVATE)
        showDividers = prefs.getBoolean(
                "dividers", true)

        if (showDividers)
            recyclerView!!.addItemDecoration(
                    DividerItemDecoration(
                            this, LinearLayoutManager.VERTICAL))
        else {
            if (recyclerView!!.itemDecorationCount > 0)
                recyclerView!!.removeItemDecorationAt(0)
        }
    }

    private fun saveNotes() {
        try {
            mSerializer!!.save(this.noteList!!)
        } catch (e: Exception) {
            Log.e("Error Saving Notes", "", e)
        } }

    override fun onPause() {
        super.onPause()
        saveNotes()
    }

}

