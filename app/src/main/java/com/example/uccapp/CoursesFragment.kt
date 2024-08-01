package com.example.uccapp

import CourseAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore

class CoursesFragment : Fragment() {
    private lateinit var db: FirebaseFirestore
    private lateinit var recyclerView: RecyclerView
    private lateinit var courseList: ArrayList<Course>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_courses, container, false)
        recyclerView = view.findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(context)
        courseList = ArrayList()
        db = FirebaseFirestore.getInstance()
        fetchCourseData()
        return view
    }

    private fun fetchCourseData() {
        db.collection("courses")
            .get()
            .addOnSuccessListener { result ->
                courseList.clear()
                for (document in result) {
                    val course = document.toObject(Course::class.java)
                    courseList.add(course)
                }
                recyclerView.adapter = CourseAdapter(courseList)
            }
            .addOnFailureListener { exception ->
                // Handle error
            }
    }
}

data class Course(
    val code: String = "",
    val name: String = "",
    val credits: Int = 0,
    val prerequisites: String = "",
    val description: String = ""
)