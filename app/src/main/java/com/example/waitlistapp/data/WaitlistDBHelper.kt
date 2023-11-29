package com.example.waitlistapp.data

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class WaitlistDBHelper(context: Context) :
  SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

  companion object {
    const val DATABASE_NAME = "waitlist.db"
    const val DATABASE_VERSION = 1
  }

  override fun onCreate(db: SQLiteDatabase?) {
    val sql = """
      CREATE TABLE ${WaitlistContract.WaitlistEntry.TABLE_NAME} (
      ${WaitlistContract.WaitlistEntry.COLUMN_GUEST_ID} INTEGER PRIMARY KEY AUTO INCREMENT,
      ${WaitlistContract.WaitlistEntry.COLUMN_GUEST_NAME} TEXT NOT NULL,
      ${WaitlistContract.WaitlistEntry.COLUMN_GUEST_NUMBER} INTEGER NOT NULL,
      ${WaitlistContract.WaitlistEntry.COLUMN_GUEST_TIMESTAMP} TIMESTAMP DEFAULT CURRENT_TIMESTAMP
      );
    """.trimIndent()

    db?.execSQL(sql)
  }

  override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    val sql = "DROP TABlE IF EXISTS ${WaitlistContract.WaitlistEntry.TABLE_NAME}"

    db?.execSQL(sql)
    onCreate(db)
  }
}