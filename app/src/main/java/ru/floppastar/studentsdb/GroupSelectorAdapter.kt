package ru.floppastar.studentsdb

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class GroupSelectorAdapter(
    var groupList: MutableList<StudentGroup>,
    private val GroupSelectListener: (StudentGroup) -> Unit)
    : RecyclerView.Adapter<GroupAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvGroupName = itemView.findViewById<TextView>(R.id.tvGroupName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.group_selector_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return groupList.size
    }

    override fun onBindViewHolder(holder: GroupAdapter.ViewHolder, position: Int) {
        var group = groupList[position]
        holder.tvGroupName.text = group.groupName
        holder.tvGroupName.setOnClickListener {
            GroupSelectListener(group)
        }
    }
}