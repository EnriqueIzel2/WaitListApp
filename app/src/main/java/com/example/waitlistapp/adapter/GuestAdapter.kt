package com.example.waitlistapp.adapter

import android.database.Cursor
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.waitlistapp.R
import com.example.waitlistapp.data.WaitlistContract

class GuestAdapter(private var cursor: Cursor) :
  RecyclerView.Adapter<GuestAdapter.GuestViewHolder>() {

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GuestViewHolder {
    val context = parent.context
    val layoutResID = R.layout.guest_list_item
    val inflater = LayoutInflater.from(context)

    val view = inflater.inflate(layoutResID, parent, false)

    return GuestViewHolder(view)
  }

  override fun onBindViewHolder(holder: GuestViewHolder, position: Int) {
    if (!cursor.moveToPosition(position)) {
      return
    }

    val guestIdIndex = cursor.getColumnIndex(WaitlistContract.WaitlistEntry.COLUMN_GUEST_ID)
    val guestNameIndex = cursor.getColumnIndex(WaitlistContract.WaitlistEntry.COLUMN_GUEST_NAME)
    val guestNumberIndex = cursor.getColumnIndex(WaitlistContract.WaitlistEntry.COLUMN_GUEST_NUMBER)

    val guestId = cursor.getInt(guestIdIndex)
    val guestName = cursor.getString(guestNameIndex)
    val guestNumber = cursor.getInt(guestNumberIndex)

    holder.textViewGuestName.text = guestName
    holder.textViewGuestNumber.text = guestNumber.toString()
    holder.itemView.tag = guestId
  }

  override fun getItemCount(): Int = cursor.count

  fun swapCursor(newCursor: Cursor) {
    cursor.close()

    newCursor.let {
      cursor = it
    }

    notifyDataSetChanged()
  }

  inner class GuestViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    var textViewGuestName: TextView
    var textViewGuestNumber: TextView

    init {
      textViewGuestName = view.findViewById(R.id.textView_guest_name)
      textViewGuestNumber = view.findViewById(R.id.textView_guest_number)
    }
  }
}