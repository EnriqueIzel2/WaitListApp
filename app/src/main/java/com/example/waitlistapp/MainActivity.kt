package com.example.waitlistapp

import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.waitlistapp.adapter.GuestAdapter
import com.example.waitlistapp.data.WaitlistContract
import com.example.waitlistapp.data.WaitlistDBHelper

class MainActivity : AppCompatActivity() {

  private lateinit var sqliteDatabase: SQLiteDatabase
  private lateinit var editTextGuestName: EditText
  private lateinit var editTextGuestNumber: EditText
  private lateinit var adapter: GuestAdapter

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    val waitlistDBHelper = WaitlistDBHelper(this)
    sqliteDatabase = waitlistDBHelper.writableDatabase

    val cursor = getAllGuests()
    val recyclerView: RecyclerView = findViewById(R.id.recyclerView)

    editTextGuestName = findViewById(R.id.editText_guest_name)
    editTextGuestNumber = findViewById(R.id.editText_guest_number)

    adapter = GuestAdapter(cursor)
    recyclerView.layoutManager = LinearLayoutManager(this)
    recyclerView.adapter = adapter

    val callback =
      object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
        override fun onMove(
          recyclerView: RecyclerView,
          viewHolder: RecyclerView.ViewHolder,
          target: RecyclerView.ViewHolder
        ): Boolean {
          return false
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
          val id = viewHolder.itemView.tag as Int

          removeGuest(id)

          adapter.swapCursor(getAllGuests())
        }
      }

    ItemTouchHelper(callback).attachToRecyclerView(recyclerView)
  }

  private fun removeGuest(id: Int) {
    sqliteDatabase.delete(
      WaitlistContract.WaitlistEntry.TABLE_NAME,
      "${WaitlistContract.WaitlistEntry.COLUMN_GUEST_ID}=$id",
      null
    )
  }

  private fun getAllGuests(): Cursor {
    return sqliteDatabase.query(
      WaitlistContract.WaitlistEntry.TABLE_NAME,
      null,
      null,
      null,
      null,
      null,
      WaitlistContract.WaitlistEntry.COLUMN_GUEST_TIMESTAMP
    )
  }

  fun onClickAddGuest(view: View) {

    val guestName = editTextGuestName.text.toString()
    val guestNumber = editTextGuestNumber.text.toString()

    if (guestName.isEmpty() || guestNumber.isEmpty()) {
      return
    }

    addNewGuest(guestName, guestNumber)

    adapter.swapCursor(getAllGuests())

    editTextGuestName.text.clear()
    editTextGuestNumber.text.clear()
  }

  private fun addNewGuest(guestName: String, guestNumber: String) {
    val contentValues = ContentValues()

    contentValues.put(WaitlistContract.WaitlistEntry.COLUMN_GUEST_NAME, guestName)
    contentValues.put(WaitlistContract.WaitlistEntry.COLUMN_GUEST_NUMBER, guestNumber)

    sqliteDatabase.insert(WaitlistContract.WaitlistEntry.TABLE_NAME, null, contentValues)
  }
}