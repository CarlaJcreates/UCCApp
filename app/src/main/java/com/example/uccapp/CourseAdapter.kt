import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.uccapp.Course
import com.example.uccapp.R

class CourseAdapter(private val courseList: ArrayList<Course>) : RecyclerView.Adapter<CourseAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val courseCode: TextView = view.findViewById(R.id.course_code)
        val courseName: TextView = view.findViewById(R.id.course_name)
        val courseCredits: TextView = view.findViewById(R.id.course_credits)
        val coursePrerequisites: TextView = view.findViewById(R.id.course_prerequisites)
        val courseDescription: TextView = view.findViewById(R.id.course_description)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_course, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val course = courseList[position]
        holder.courseCode.text = course.code
        holder.courseName.text = course.name
        holder.courseCredits.text = course.credits.toString()
        holder.coursePrerequisites.text = course.prerequisites
        holder.courseDescription.text = course.description
    }

    override fun getItemCount(): Int {
        return courseList.size
    }
}
