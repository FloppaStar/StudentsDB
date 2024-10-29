package ru.floppastar.studentsdb

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.floppastar.studentsdb.db.StudentRepository

class StudentAdapter(
    var studentList: MutableList<Student>,
    var studentRepository: StudentRepository,
    private val StudentDeleteListener: (Int) -> Unit,
    private val StudentEditListener: (Student, Int) -> Unit)
    : RecyclerView.Adapter<StudentAdapter.ViewHolder>()   {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvFirstName = itemView.findViewById<TextView>(R.id.tvFirstName)
        val tvSecondName = itemView.findViewById<TextView>(R.id.tvSecondName)
        val tvAge = itemView.findViewById<TextView>(R.id.tvAge)
        val tvGroup = itemView.findViewById<TextView>(R.id.tvGroup)
        val btEdit = itemView.findViewById<ImageView>(R.id.imEdit)
        val btDelete = itemView.findViewById<ImageView>(R.id.imDelete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.student_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: StudentAdapter.ViewHolder, position: Int) {
        var student = studentList[position]
        holder.tvFirstName.text = student.firstName
        holder.tvSecondName.text = student.secondName
        holder.tvAge.text = student.age.toString()
        holder.tvGroup.text = studentRepository.getGroupName(student.groupId)
        holder.btDelete.setOnClickListener {
            StudentDeleteListener(position)
            Log.d("LOH","$position ${student.firstName} " )
            studentList.removeAt(position)
            notifyItemRemoved(position)
            update(studentList)
        }
        holder.btEdit.setOnClickListener {
            StudentEditListener(student, position)
            notifyItemChanged(position)
        }
    }

    override fun getItemCount(): Int {
        return studentList.size
    }

    fun update(newList: MutableList<Student>) {
        studentList = newList
        notifyDataSetChanged()
    }
}