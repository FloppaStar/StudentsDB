package ru.floppastar.studentsdb

data class Student(
    val studentId: Long,
    var firstName: String,
    var secondName: String,
    var age: Int,
    var groupId: Long
)
