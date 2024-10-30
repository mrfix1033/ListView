package ru.mrfix1033.listview

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import ru.mrfix1033.listview.data.User

class MainActivity : AppCompatActivity() {

    lateinit var toolbar: Toolbar
    lateinit var listView: ListView
    lateinit var editTextName: EditText
    lateinit var editTextAge: EditText
    lateinit var buttonAdd: Button

    val usersList: MutableList<User> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar = findViewById(R.id.toolbar)
        listView = findViewById(R.id.listView)
        editTextName = findViewById(R.id.editTextName)
        editTextAge = findViewById(R.id.editTextAge)
        buttonAdd = findViewById(R.id.buttonAdd)

        setSupportActionBar(toolbar)
        title = getString(R.string.catalog_of_users)

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, usersList)
        listView.adapter = adapter

        buttonAdd.setOnClickListener {
            val age = editTextAge.text.toString().toIntOrNull() ?: return@setOnClickListener
            usersList.add(
                User(
                    editTextName.text.toString(),
                    age
                )
            )
            editTextName.text.clear()
            editTextAge.text.clear()
            adapter.notifyDataSetChanged()
        }

        listView.onItemClickListener = AdapterView.OnItemClickListener {
            parent, view, position, id ->
            usersList.removeAt(position)
            adapter.notifyDataSetChanged()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menuItemExit -> finish()
        }
        return true
    }
}