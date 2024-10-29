package ru.floppastar.studentsdb

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import ru.floppastar.studentsdb.db.DatabaseHelper
import ru.floppastar.studentsdb.db.StudentRepository

class EditStudentFragment(var student: Student) : Fragment() {
    private lateinit var studentRepository: StudentRepository
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit_student, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val firstName = view.findViewById<EditText>(R.id.etStudentFirstName)
        val secondName = view.findViewById<EditText>(R.id.etStudentSecondName)
        val age = view.findViewById<EditText>(R.id.etAge)
        val group = view.findViewById<EditText>(R.id.etGroup)
        firstName.setText(student.firstName)
        secondName.setText(student.secondName)
        age.setText(student.age)
        val btSave = view.findViewById<Button>(R.id.btSaveStudent)
        studentRepository = StudentRepository(DatabaseHelper(view.context))
        btSave.setOnClickListener {
            student.firstName = firstName.text.toString()
            student.secondName = secondName.text.toString()
            student.age = age.text.toString().toInt()
            studentRepository.editStudent(student)
            parentFragmentManager.popBackStack()
        }
    }
}