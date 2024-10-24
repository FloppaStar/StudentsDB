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

class GroupsFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var groupRepository: GroupRepository
    private lateinit var groupAdapter: GroupAdapter
    private var groupList: MutableList<StudentGroup> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_groups, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        groupRepository = GroupRepository(DatabaseHelper(view.context))
        recyclerView = view.findViewById(R.id.groupRecyclerView)
        groupList = groupRepository.getAllGroups()
        groupAdapter = GroupAdapter(groupList, { position ->
            groupRepository.deleteGroup(groupList[position].groupId)
            groupList.removeAt(position)
        }, { studentGroup, position ->
            val dialog = BottomSheetDialog(view.context)
            val view = layoutInflater.inflate(R.layout.group_bottom_sheet_dialog, null)
            val groupName = view.findViewById<EditText>(R.id.etGroupName)
            groupName.setText(studentGroup.groupName)
            val btClose = view.findViewById<Button>(R.id.btSaveGroup)
            btClose.setOnClickListener {
                groupRepository.insertGroup(groupName.text.toString())
                groupAdapter.update(groupRepository.getAllGroups())
                groupList = groupRepository.getAllGroups()
                dialog.dismiss()
            }
            dialog.setCancelable(false)
            dialog.setContentView(view)
            dialog.show()
        })

        recyclerView.adapter = groupAdapter

        val btAddGroup = view.findViewById<FloatingActionButton>(R.id.fabAddGroup)
        btAddGroup.setOnClickListener {
            val dialog = BottomSheetDialog(view.context)
            val view = layoutInflater.inflate(R.layout.group_bottom_sheet_dialog, null)
            val groupName = view.findViewById<EditText>(R.id.etGroupName)
            val btClose = view.findViewById<Button>(R.id.btSaveGroup)
            btClose.setOnClickListener {
                groupRepository.insertGroup(groupName.text.toString())
                groupAdapter.update(groupRepository.getAllGroups())
                groupList = groupRepository.getAllGroups()
                dialog.dismiss()
            }
            dialog.setCancelable(false)
            dialog.setContentView(view)
            dialog.show()
        }
    }
}