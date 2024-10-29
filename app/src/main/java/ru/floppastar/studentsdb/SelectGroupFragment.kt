package ru.floppastar.studentsdb

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.floppastar.studentsdb.db.GroupRepository

class SelectGroupFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var groupRepository: GroupRepository
    private var groupList: MutableList<StudentGroup> = mutableListOf()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_select_group, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.groupSelecterRecyclerView)
        groupList = groupRepository.getAllGroups()

    }
}