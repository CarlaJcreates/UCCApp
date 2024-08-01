package com.example.uccapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore

class DirectoryFragment : Fragment() {
    private lateinit var db: FirebaseFirestore
    private lateinit var recyclerView: RecyclerView
    private lateinit var staffList: ArrayList<Staff>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_directory, container, false)
        recyclerView = view.findViewById(R.id.recycler_view)
        recyclerView.layoutManager = GridLayoutManager(context, 2) // Use GridLayoutManager with 2 columns
        staffList = ArrayList()
        db = FirebaseFirestore.getInstance()
        fetchStaffData()
        return view
    }

    private fun fetchStaffData() {
        db.collection("staff")
            .get()
            .addOnSuccessListener { result ->
                staffList.clear()
                for (document in result) {
                    val staff = document.toObject(Staff::class.java)
                    staffList.add(staff)
                }
                recyclerView.adapter = StaffAdapter(staffList)
            }
            .addOnFailureListener { exception ->
                // Handle error
            }
    }
}

data class Staff(
    val name: String = "",
    val email: String = "",
    val phone: String = "",
    val photo: String = ""
)
