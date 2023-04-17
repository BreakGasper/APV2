package com.breakapp.apv2

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class PizzaApplication: Application() {
    /*companion object{

          private var database: TaskDatabase?=null

          fun  getDataBase(context: Context): TaskDatabase{
              database = database ?: Room.databaseBuilder(
                  context.applicationContext,
                  TaskDatabase::class.java,
                  "task-db"
              ).build()
              return database!!
          }

          suspend fun destroyInstance() {
             database = null
          }
      }*/
}