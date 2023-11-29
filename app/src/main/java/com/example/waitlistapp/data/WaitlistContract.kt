package com.example.waitlistapp.data

class WaitlistContract {

  object WaitlistEntry {
    const val TABLE_NAME = "waitlist"
    const val COLUMN_GUEST_ID = "_id"
    const val COLUMN_GUEST_NAME = "guest_name"
    const val COLUMN_GUEST_NUMBER = "guest_number"
    const val COLUMN_GUEST_TIMESTAMP = "guest_date"
  }
}