package ru.floppastar.studentsdb.db

import android.content.ContentValues
import ru.floppastar.studentsdb.StudentGroup

class GroupRepository(private val dbHelper: DatabaseHelper) {
    fun insertGroup(groupName: String) {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put(DatabaseHelper.COLUMN_GROUP_NAME, groupName)
        }
        db.insert(DatabaseHelper.TABLE_GROUP, null, values)
        db.close()
    }
    fun getAllGroups(): MutableList<StudentGroup> {
        val db = dbHelper.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM ${DatabaseHelper.TABLE_GROUP}", null)
        val groups = mutableListOf<StudentGroup>()

        with(cursor){
            while(moveToNext()) {
                val id = getLong(getColumnIndexOrThrow(DatabaseHelper.COLUMN_GROUP_ID))
                val name = getString(getColumnIndexOrThrow(DatabaseHelper.COLUMN_GROUP_NAME))
                groups.add(StudentGroup(id, name))
            }
        }
        cursor.close()
        db.close()
        return groups
    }
    fun editGroup(group: StudentGroup){
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put(DatabaseHelper.COLUMN_GROUP_NAME, group.groupName)
        }
        db.update(DatabaseHelper.TABLE_GROUP, values, "${DatabaseHelper.COLUMN_GROUP_ID} = ${group.groupId}", null)
        db.close()
    }
    fun deleteGroup(groupId: Long) {
        val db = dbHelper.writableDatabase
        db.delete(DatabaseHelper.TABLE_GROUP,
            "${DatabaseHelper.COLUMN_GROUP_ID} = $groupId", null)
        db.close()
    }
}