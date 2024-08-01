package com.example.uccapp

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class StaffAdapter(private val staffList: ArrayList<Staff>) : RecyclerView.Adapter<StaffAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val staffName: TextView = view.findViewById(R.id.staff_name)
        val staffEmail: TextView = view.findViewById(R.id.staff_email)
        val staffPhone: TextView = view.findViewById(R.id.staff_phone)
        val staffPhoto: ImageView = view.findViewById(R.id.staff_photo)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_staff, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val staff = staffList[position]
        holder.staffName.text = staff.name
        holder.staffEmail.text = staff.email
        holder.staffPhone.text = staff.phone

        // Load image using Glide
        Glide.with(holder.staffPhoto.context)
            .load(staff.photo)
            .placeholder(R.drawable.ic_baseline_people_24)  // Placeholder image
            .error(R.drawable.ic_baseline_error_24)  // Error image
            .into(holder.staffPhoto)

        // Click listener for email
        holder.staffEmail.setOnClickListener {
            val intent = Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("mailto:${staff.email}")
            }
            holder.staffEmail.context.startActivity(intent)
        }

        // Click listener for phone
        holder.staffPhone.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL).apply {
                data = Uri.parse("tel:${staff.phone}")
            }
            holder.staffPhone.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return staffList.size
    }
}
