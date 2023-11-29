package com.example.waitlistapp

import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.waitlistapp.adapter.GuestAdapter
import com.example.waitlistapp.data.WaitlistContract
import com.example.waitlistapp.data.WaitlistDBHelper

class MainActivity : AppCompatActivity() {

  lateinit var sqliteDatabase: SQLiteDatabase
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    val cursor = getAllGuests()
    val waitlistDBHelper = WaitlistDBHelper(this)
    val recyclerView: RecyclerView = findViewById(R.id.recyclerView)

    recyclerView.layoutManager = LinearLayoutManager(this)
    recyclerView.adapter = GuestAdapter(cursor)

    sqliteDatabase = waitlistDBHelper.writableDatabase
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

  fun onClickAddGuest(view: View) {}
}