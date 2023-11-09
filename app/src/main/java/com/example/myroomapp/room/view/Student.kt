package com.example.myroomapp.room.view

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Junction
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity(tableName = "student")
data class Student(
    @PrimaryKey(autoGenerate = true)
    val student_id: Int?,
    val student_name: String,
    var course_name: String,
)

@Entity(tableName = "teacher")
data class Teacher(
    @PrimaryKey(autoGenerate = true)
    val teacher_id: Int?,
    val teacher_name: String,
    val course_taken:String,
)
@Entity(tableName = "student_teacher")
data class StudentTeacherCrossRef(
    @PrimaryKey(autoGenerate = true)
    val id: Int?,
    val student_id: Int,
    val teacher_id: Int
)
@Entity
data class studentandteacher(
   @Embedded val student:List<Student>,
    @Relation(
        parentColumn = "student_id",
        entityColumn = "teacher_id",
        associateBy = Junction(StudentTeacherCrossRef::class)
    )
    val teacher: List<Teacher>
)
