package io.sokolvault.wayofturtles

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import io.sokolvault.wayofturtles.db.BigGoal
import io.sokolvault.wayofturtles.db.GoalsDatabase

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val database: GoalsDatabase = GoalsDatabase.getInstance(this)
//        database.bigGoalDAO().all
    }
}