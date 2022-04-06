package com.example.tugasramadhanday2and3

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private val newNameActivityRequestCode = 1
    private val nameViewModel: NameViewModel by viewModels {
        WordViewModelFactory((application as NamesApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        val adapter = NameListAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        nameViewModel.allNames.observe(this) { words ->
            words.let { adapter.submitList(it) }
        }

        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener {
            val intent = Intent(this@MainActivity, NewNameActivity::class.java)
            startActivityForResult(intent, newNameActivityRequestCode)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, intentData: Intent?) {
        super.onActivityResult(requestCode, resultCode, intentData)

        if (requestCode == newNameActivityRequestCode && resultCode == Activity.RESULT_OK) {
            intentData?.getStringExtra(NewNameActivity.EXTRA_REPLY)?.let { reply ->
                val name = Name(reply)
                nameViewModel.insert(name)
            }
        } else {
            Toast.makeText(
                applicationContext,
                "Cannot be Empty",
                Toast.LENGTH_LONG
            ).show()
        }
    }
}