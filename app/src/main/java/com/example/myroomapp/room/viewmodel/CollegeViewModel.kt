package com.example.myroomapp.room.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.myroomapp.room.CollegeRepository
import com.example.myroomapp.room.database.CollegeDatabase
import com.example.myroomapp.room.view.Student
import com.example.myroomapp.room.view.Teacher

class CollegeViewModel(application: Application):AndroidViewModel(Application()) {

    val getStudentData: LiveData<List<Student>>
    private val getTeacherData: LiveData<List<Teacher>>
    private val repository: CollegeRepository
    init {
        val dao = CollegeDatabase.getDatabaase(application).collegeDao()
        repository = CollegeRepository(dao)
        getTeacherData= repository.getTeacherList!!
        getStudentData= repository.getStudentList!!
    }

    fun addTeacher(teacher: ArrayList<Teacher>){
        repository.addTeacher(teacher)
    }
    suspend fun getTeacherNameByStudentAndCourse(studentName: String, courseName: String): String?{
        return repository.getTeacherNameByStudentAndCourse(studentName, courseName)
    }

    fun addStudent(student: ArrayList<Student>){
        for (student in student) {
            Log.e("Inserted Student", "Student Name: ${student.student_name}, Course: ${student.course_name}")
            // Insert the student data into the database
        }
        repository.addStudent(student)

    }
}