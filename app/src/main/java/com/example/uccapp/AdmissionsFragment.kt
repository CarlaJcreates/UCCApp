package com.example.uccapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment

class AdmissionsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_admissions, container, false)

        val admissionsInfo = """
            Entry Requirements for Jamaican Students:
            
            - Minimum of five (5) subjects at the GCE or CSEC level, including English Language and Mathematics, with grades A, B, C or 1, 2, 3.
            - Candidates with at least four (4) CXCs can apply if they are pending the remaining subjects or take UCC replacement courses.
            - Mature Entry Option: Candidates aged 25 or older with at least 5 years of work experience can apply. A detailed resume, job letter, and three professional references are required.
        """.trimIndent()

        val textView: TextView = view.findViewById(R.id.admissions_text)
        textView.text = admissionsInfo

        val button: Button = view.findViewById(R.id.apply_button)
        button.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.ucc.edu.jm/apply"))
            startActivity(intent)
        }

        return view
    }
}
