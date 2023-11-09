package com.example.myroomapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.example.myroomapp.databinding.ActivityMainBinding
import com.example.myroomapp.room.database.CollegeDatabase
import com.example.myroomapp.room.view.Student
import com.example.myroomapp.room.view.Teacher
import com.example.myroomapp.room.viewmodel.CollegeViewModel

class MainActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var mainActivity:ActivityMainBinding
    lateinit var collegeViewModel: CollegeViewModel
    val Studentdetail: ArrayList<Student> = ArrayList()
    val teacherdetail: ArrayList<Teacher> = ArrayList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivity  = DataBindingUtil.setContentView(this ,R.layout.activity_main )
        val database = Room.databaseBuilder(applicationContext, CollegeDatabase::class.java, "database").build()
        collegeViewModel = ViewModelProvider(this).get(CollegeViewModel::class.java)
        diInitContent()
    }

    private fun diInitContent() {
      mainActivity.btnAdd.setOnClickListener(this)
        mainActivity.btnList.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
       when(v){
           mainActivity.btnAdd->{
               doCheckValidation()
           }
           mainActivity.btnList->{
               val intent = Intent(this, DetailListActivity::class.java)
               startActivity(intent)
           }
       }
    }

    private fun doCheckValidation() {
      val studentName=mainActivity.studentame.text.trim().toString()
        val studentCourse=mainActivity.studentcourse.text.trim().toString()
        val teacherName=mainActivity.teacherame.text.trim().toString()
        val teacherCourse=mainActivity.teachercourse.text.trim().toString()

        if (studentName.isEmpty()){
            Toast.makeText(applicationContext,"enter the student name",Toast.LENGTH_SHORT).show()
        }else if(studentCourse.isEmpty()){
            Toast.makeText(applicationContext,"enter the student coursename",Toast.LENGTH_SHORT).show()
        }else if(teacherName.isEmpty()){
            Toast.makeText(applicationContext,"enter the Teacher name",Toast.LENGTH_SHORT).show()
        }else if(teacherCourse.isEmpty()){
            Toast.makeText(applicationContext,"enter the Teacher Coursename",Toast.LENGTH_SHORT).show()
        }else{
            Studentdetail.add(Student(0 ,studentName,studentCourse))
            teacherdetail.add(Teacher(0 ,teacherName,teacherCourse))
           collegeViewModel.addStudent(Studentdetail)
            collegeViewModel.addTeacher(teacherdetail)
            mainActivity.studentame.text.clear()
            mainActivity.studentcourse.text.clear()
            mainActivity.teacherame.text.clear()
            mainActivity.teachercourse.text.clear()
        }
    }
}