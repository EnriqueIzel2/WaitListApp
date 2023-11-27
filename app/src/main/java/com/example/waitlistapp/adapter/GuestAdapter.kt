package com.example.waitlistapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.waitlistapp.R

class GuestAdapter : RecyclerView.Adapter<GuestAdapter.GuestViewHolder>() {

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GuestViewHolder {
    val context = parent.context
    val layoutResID = R.layout.guest_list_item
    val inflater = LayoutInflater.from(context)

    val view = inflater.inflate(layoutResID, parent, false)

    return GuestViewHolder(view)
  }

  override fun onBindViewHolder(holder: GuestViewHolder, position: Int) {
    TODO("Not yet implemented")
  }

  override fun getItemCount(): Int {
    TODO("Not yet implemented")
  }

  inner class GuestViewHolder(view: View) : RecyclerView.ViewHolder(view) {

  }
}