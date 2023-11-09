package com.example.myroomapp.room.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.myroomapp.room.view.Student
import com.example.myroomapp.room.view.Teacher


@Dao
interface Dao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addStudent(student: ArrayList<Student>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addTeacher(teacher: ArrayList<Teacher>)

    @Query("SELECT * FROM student")
    fun getStudentList(): LiveData<List<Student>>
    @Query("SELECT * FROM teacher")
    fun getTeacherList(): LiveData<List<Teacher>>

    @Query("SELECT teacher.teacher_name FROM teacher " +
            "INNER JOIN student_teacher ON teacher.teacher_id = student_teacher.teacher_id " +
            "INNER JOIN student ON student_teacher.student_id = student.student_id " +
            "WHERE student.student_name = :studentName AND student.course_name = :courseName")
    suspend fun getTeacherNameByStudentAndCourse(studentName: String, courseName: String): String?
}