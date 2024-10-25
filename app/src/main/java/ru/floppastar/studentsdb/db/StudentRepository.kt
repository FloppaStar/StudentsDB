package ru.floppastar.studentsdb.db

import android.content.ContentValues
import ru.floppastar.studentsdb.Student

class StudentRepository(private val dbHelper: DatabaseHelper) {
    fun insertStudent(firstName: String, secondName: String, age: Int, groupId: Long) {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put(DatabaseHelper.COLUMN_STUDENT_FIRSTNAME, firstName)
            put(DatabaseHelper.COLUMN_STUDENT_SECONDNAME, secondName)
            put(DatabaseHelper.COLUMN_STUDENT_AGE, age)
            put(DatabaseHelper.COLUMN_STUDENT_GROUP, groupId)
        }
        db.insert(DatabaseHelper.TABLE_STUDENT, null, values)
        db.close()
    }
    fun getAllStudents(): MutableList<Student> {
        val db = dbHelper.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM ${DatabaseHelper.TABLE_STUDENT}", null)
        val students = mutableListOf<Student>()

        with(cursor){
            while(moveToNext()) {
                val id = getLong(getColumnIndexOrThrow(DatabaseHelper.COLUMN_STUDENT_ID))
                val firstName = getString(getColumnIndexOrThrow(DatabaseHelper.COLUMN_STUDENT_FIRSTNAME))
                val secondName = getString(getColumnIndexOrThrow(DatabaseHelper.COLUMN_STUDENT_SECONDNAME))
                val age = getInt(getColumnIndexOrThrow(DatabaseHelper.COLUMN_STUDENT_AGE))
                val groupId = getLong(getColumnIndexOrThrow(DatabaseHelper.COLUMN_STUDENT_GROUP))
                students.add(Student(id, firstName, secondName, age, groupId))
            }
        }
        cursor.close()
        db.close()
        return students
    }
    fun getGroupName(groupId: Long): String {
        val db = dbHelper.readableDatabase
        val cursor = db.rawQuery("SELECT ${DatabaseHelper.COLUMN_GROUP_NAME} FROM ${DatabaseHelper.TABLE_GROUP} " +
                "WHERE ${DatabaseHelper.COLUMN_GROUP_ID} = $groupId", null)
        val groupName = cursor.getString(groupId.toInt())
        cursor.close()
        db.close()
        return groupName
    }
    fun editStudent(student: Student){
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put(DatabaseHelper.COLUMN_STUDENT_FIRSTNAME, student.firstName)
            put(DatabaseHelper.COLUMN_STUDENT_SECONDNAME, student.secondName)
            put(DatabaseHelper.COLUMN_STUDENT_AGE, student.age)
            put(DatabaseHelper.COLUMN_STUDENT_GROUP, student.groupId)
        }
        db.update(DatabaseHelper.TABLE_STUDENT, values, "${DatabaseHelper.COLUMN_STUDENT_ID} = ${student.studentId}", null)
        db.close()
    }
    fun deleteStudent(studentId: Long) {
        val db = dbHelper.writableDatabase
        db.delete(DatabaseHelper.TABLE_STUDENT,
            "${DatabaseHelper.COLUMN_STUDENT_ID} = $studentId", null)
        db.close()
    }
}