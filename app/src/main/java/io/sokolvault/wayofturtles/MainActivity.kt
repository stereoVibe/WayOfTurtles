package io.sokolvault.wayofturtles

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import io.sokolvault.wayofturtles.model.BigGoal
import io.sokolvault.wayofturtles.model.Job
import io.sokolvault.wayofturtles.repository.DaggerGoalsDAOApplicationComponent
import io.sokolvault.wayofturtles.utils.DbOps.Companion.asyncInsert
import io.sokolvault.wayofturtles.utils.DbOps.Companion.asyncSubGoalInsert

@Suppress("EXPERIMENTAL_FEATURE_WARNING")
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val goalsDataComponent = DaggerGoalsDAOApplicationComponent.builder()
                .contextModule(ContextModule(this))
                .build()

        val bigGoalDAO = goalsDataComponent.getBigGoalDao()
        bigGoalDAO.asyncInsert(BigGoal("Вставка в БД через расширяемую функцию"))

        val jobDAO = goalsDataComponent.getJobDao()
//        val taskDAO = goalsDataComponent.getTaskDao()

        jobDAO.asyncSubGoalInsert(Job.newBuilder()
                .setCompositeGoalID(1)
                .setId(1)
                .setTitle("This is Job!")
                .setTasksQuantity(20)
                .build())
    }


}
