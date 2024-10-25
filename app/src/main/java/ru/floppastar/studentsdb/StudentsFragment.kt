package ru.floppastar.studentsdb

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.floatingactionbutton.FloatingActionButton
import ru.floppastar.studentsdb.db.DatabaseHelper
import ru.floppastar.studentsdb.db.GroupRepository
import ru.floppastar.studentsdb.db.StudentRepository

class StudentsFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var studentRepository: StudentRepository
    private lateinit var studentAdapter: StudentAdapter
    private var studentList: MutableList<Student> = mutableListOf()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_students, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        studentRepository = StudentRepository(DatabaseHelper(view.context))
        recyclerView = view.findViewById(R.id.studentRecyclerView)
        studentList = studentRepository.getAllStudents()
//        studentAdapter = StudentAdapter(studentList, { position ->
//            studentRepository.deleteStudent(studentList[position].studentId)
//            studentList.removeAt(position)
//        }, { studentGroup, position ->
//            val dialog = BottomSheetDialog(view.context)
//            val view = layoutInflater.inflate(R.layout.group_bottom_sheet_dialog, null)
//            val groupName = view.findViewById<EditText>(R.id.etGroupName)
//            groupName.setText(studentGroup.groupName)
//            val btClose = view.findViewById<Button>(R.id.btSaveGroup)
//            btClose.setOnClickListener {
//                studentGroup.groupName = groupName.text.toString()
//                groupRepository.editGroup(studentGroup)
//                groupAdapter.update(groupRepository.getAllGroups())
//                groupList = groupRepository.getAllGroups()
//                dialog.dismiss()
//            }
//            dialog.setCancelable(false)
//            dialog.setContentView(view)
//            dialog.show()
//        })

        recyclerView.adapter = studentAdapter

//        val btAddGroup = view.findViewById<FloatingActionButton>(R.id.fabAddGroup)
//        btAddGroup.setOnClickListener {
//            val dialog = BottomSheetDialog(view.context)
//            val view = layoutInflater.inflate(R.layout.group_bottom_sheet_dialog, null)
//            val groupName = view.findViewById<EditText>(R.id.etGroupName)
//            val btClose = view.findViewById<Button>(R.id.btSaveGroup)
//            btClose.setOnClickListener {
//                groupRepository.insertGroup(groupName.text.toString())
//                groupAdapter.update(groupRepository.getAllGroups())
//                groupList = groupRepository.getAllGroups()
//                dialog.dismiss()
//            }
//            dialog.setCancelable(false)
//            dialog.setContentView(view)
//            dialog.show()
//        }
    }
}