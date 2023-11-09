package com.example.myroomapp.room.database

import android.content.Context
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.myroomapp.room.view.Student
import com.example.myroomapp.room.view.StudentTeacherCrossRef
import com.example.myroomapp.room.view.Teacher
import com.example.myroomapp.room.view.studentandteacher

@Database(entities = [Student::class,Teacher::class, StudentTeacherCrossRef::class], version = 1)
abstract class CollegeDatabase :RoomDatabase(){

abstract  fun collegeDao(): com.example.myroomapp.room.database.dao.Dao
    companion object {
        @Volatile
        private var INSTANCE: CollegeDatabase? = null
        fun getDatabaase(context: Context): CollegeDatabase {
            var tempinstance = INSTANCE
            if (tempinstance != null) {

                return tempinstance
            }
            synchronized(this){
                val instance= Room.databaseBuilder(context.applicationContext,CollegeDatabase::class.java,"database").allowMainThreadQueries().build()
                INSTANCE=instance
                return instance
            }

        }


    }
}