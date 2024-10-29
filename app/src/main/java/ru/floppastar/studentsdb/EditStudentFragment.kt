package ru.floppastar.studentsdb

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import ru.floppastar.studentsdb.db.DatabaseHelper
import ru.floppastar.studentsdb.db.StudentRepository

class EditStudentFragment(var student: Student?) : Fragment() {
    private lateinit var studentRepository: StudentRepository
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit_student, container, false)
    }
    lateinit var firstName: EditText
    lateinit var secondName: EditText
    lateinit var age: EditText
    lateinit var group: EditText
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        firstName = view.findViewById(R.id.etStudentFirstName)
        secondName = view.findViewById<EditText>(R.id.etStudentSecondName)
        age = view.findViewById<EditText>(R.id.etAge)
        group = view.findViewById<EditText>(R.id.etGroup)
        if (student != null) {
            initializeFragment()
        }
        else
            student = Student(0, "", "", 0, 0)
        val btSave = view.findViewById<Button>(R.id.btSaveStudent)
        studentRepository = StudentRepository(DatabaseHelper(view.context))
        btSave.setOnClickListener {
            saveDataStudent()
            try {
                if(student!!.studentId != 0.toLong())
                    studentRepository.editStudent(student!!)
                else
                    studentRepository.insertStudent(student!!.firstName, student!!.secondName, student!!.age, student!!.groupId)
                parentFragmentManager.popBackStack()
            }
            catch (e: Exception){
                Log.d("uwu", "Error ${e.message}")
            }
        }
    }
    private fun saveDataStudent(){
        student!!.firstName = firstName.text.toString()
        student!!.secondName = secondName.text.toString()
        student!!.age = age.text.toString().toInt()
        student!!.groupId = group.text.toString().toLong()
    }
    private fun initializeFragment(){
        firstName.setText(student!!.firstName)
        secondName.setText(student!!.secondName)
        age.setText(student!!.age.toString())
        group.setText(student!!.groupId.toString())
    }
}