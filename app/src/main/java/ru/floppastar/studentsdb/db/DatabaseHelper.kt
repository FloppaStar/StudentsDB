package ru.floppastar.studentsdb.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context)
    : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object
    {
        private const val DATABASE_NAME = "students_database.db"
        private const val DATABASE_VERSION = 1

        //Таблица групп
        const val TABLE_GROUP = "StudentGroup"
        const val COLUMN_GROUP_ID = "groupId"
        const val COLUMN_GROUP_NAME = "groupName"

        //Таблица студентов
        const val TABLE_STUDENT = "Student"
        const val COLUMN_STUDENT_ID = "studentId"
        const val COLUMN_STUDENT_FIRSTNAME = "firstName"
        const val COLUMN_STUDENT_SECONDNAME = "secondName"
        const val COLUMN_STUDENT_AGE = "age"
        const val COLUMN_STUDENT_GROUP = "groupId"
    }
    override fun onCreate(db: SQLiteDatabase) {
        val createTableGroup = ("CREATE TABLE IF NOT EXISTS $TABLE_GROUP ( " +
                "$COLUMN_GROUP_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "$COLUMN_GROUP_NAME TEXT)")
        db.execSQL(createTableGroup)

        val createTableStudent = ("CREATE TABLE IF NOT EXISTS $TABLE_STUDENT ( " +
                "$COLUMN_STUDENT_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "$COLUMN_STUDENT_FIRSTNAME TEXT, " +
                "$COLUMN_STUDENT_SECONDNAME TEXT, " +
                "$COLUMN_STUDENT_AGE TEXT, " +
                "$COLUMN_STUDENT_GROUP INTEGER, " +
                "FOREIGN KEY ($COLUMN_STUDENT_GROUP)  REFERENCES $TABLE_GROUP ($COLUMN_GROUP_ID))")
        db.execSQL(createTableStudent)
    }
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_GROUP")
        db.execSQL("DROP TABLE IF EXISTS $TABLE_STUDENT")
        onCreate(db)
    }
}