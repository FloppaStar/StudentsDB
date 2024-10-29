package ru.floppastar.studentsdb

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class GroupAdapter(
    var groupList: MutableList<StudentGroup>,
    private val GroupDeleteListener: (Int) -> Unit,
    private val GroupEditListener: (StudentGroup, Int) -> Unit,
    private val GroupSelectListener: (StudentGroup) -> Unit)
    : RecyclerView.Adapter<GroupAdapter.ViewHolder>()  {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvGroupName = itemView.findViewById<TextView>(R.id.tvGroupName)
        val btEdit = itemView.findViewById<ImageView>(R.id.imEdit)
        val btDelete = itemView.findViewById<ImageView>(R.id.imDelete)
        val btSelect = itemView.findViewById<ImageView>(R.id.imSelect)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.group_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var group = groupList[position]
        holder.tvGroupName.text = group.groupName
        holder.btDelete.setOnClickListener {
            GroupDeleteListener(position)
            groupList.removeAt(position)
            notifyItemRemoved(position)
            update(groupList)
        }
        holder.btEdit.setOnClickListener {
            GroupEditListener(group, position)
            notifyItemChanged(position)
        }
        holder.btSelect.setOnClickListener{
            GroupSelectListener(group)
        }
    }

    override fun getItemCount(): Int {
        return groupList.size
    }

    fun update(newList: MutableList<StudentGroup>) {
        groupList = newList
        notifyDataSetChanged()
    }
}