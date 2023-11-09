package com.example.myroomapp.room

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.myroomapp.room.database.dao.Dao
import com.example.myroomapp.room.view.Student
import com.example.myroomapp.room.view.Teacher

class CollegeRepository(private  val dao:Dao) {
    var getTeacherList: LiveData<List<Teacher>>? = null
    var getStudentList: LiveData<List<Student>>? = null

    init {
        getTeacherList = dao.getTeacherList()
        getStudentList=dao.getStudentList()


    }
    fun addStudent(student: ArrayList<Student>){
        dao.addStudent(student)
    }
    fun addTeacher(teacher: ArrayList<Teacher>){
        dao.addTeacher(teacher)
    }
    suspend fun getTeacherNameByStudentAndCourse(studentName: String, courseName: String): String?{
        return dao.getTeacherNameByStudentAndCourse(studentName, courseName)
    }

}