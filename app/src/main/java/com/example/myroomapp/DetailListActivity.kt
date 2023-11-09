package com.example.myroomapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.example.myroomapp.databinding.ActivityDetailListBinding
import com.example.myroomapp.databinding.ActivityMainBinding
import com.example.myroomapp.room.database.CollegeDatabase
import com.example.myroomapp.room.view.Student
import com.example.myroomapp.room.view.Teacher
import com.example.myroomapp.room.viewmodel.CollegeViewModel

class DetailListActivity : AppCompatActivity(),View.OnClickListener {

    lateinit var detailListBinding:ActivityDetailListBinding
    lateinit var collegeViewModel: CollegeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailListBinding=DataBindingUtil.setContentView(this,R.layout.activity_detail_list)
        val database = Room.databaseBuilder(applicationContext, CollegeDatabase::class.java, "database").build()
//        val collegeViewModel: CollegeViewModel by lazy {
//            ViewModelProvider(this).get(CollegeViewModel::class.java)
//        }
        collegeViewModel = ViewModelProvider(this).get(CollegeViewModel::class.java)
        doInitContent()
    }

    private fun doInitContent() {
          detailListBinding.getdetail.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
     when(v){
         detailListBinding.getdetail->{

             collegeViewModel.getStudentData.observe(this, Observer { students ->
                 for (student in students) {
                     Log.e("Student Data", "${student.student_id}:${student.student_name} : ${student.course_name}")
                 }
             })
         }
     }
    }
}