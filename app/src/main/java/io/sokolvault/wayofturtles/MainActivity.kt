package io.sokolvault.wayofturtles

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import io.sokolvault.wayofturtles.data.db.model.BigGoalEntity
import io.sokolvault.wayofturtles.data.db.model.JobEntity
import io.sokolvault.wayofturtles.data.repository.DaggerGoalsDAOApplicationComponent

@Suppress("EXPERIMENTAL_FEATURE_WARNING")
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        applicationContext.deleteDatabase("goals")
        setContentView(R.layout.activity_main)

        val goalsDataComponent = DaggerGoalsDAOApplicationComponent.builder()
                .contextModule(ContextModule(this))
                .build()

        val bigGoalDAO = goalsDataComponent.getBigGoalDao()
        bigGoalDAO.asyncInsert(BigGoalEntity("Вставка в БД через расширяемую функцию"))

        val jobDAO1 = goalsDataComponent.getJobDao()

        jobDAO1.asyncSubGoalInsert(JobEntity.newBuilder()
                .setCompositeGoalID(3)
                .setId(14)
                .setTitle("This is Job 14")
                .setTasksQuantity(5)
                .build())

//        async(UI){
//            bg {
//                jobDAO2.asyncSubGoalInsert(Job.newBuilder()
//                        .setmCompositeGoalID(bigID)
//                        .setId(6)
//                        .setTitle("This is Job 6")
//                        .setTasksQuantity(20)
//                        .build())
//            }
//        }

//        HandlerThread.sleep(500)
//
//        async(UI) {
//            bg{
//                Log.d("Хуепес", jobDAO1.getAll().size.toString())
//            }
//        }
//
//        HandlerThread.sleep(500)

//        async(UI){
//            bg {
//                jobDAO.asyncSubGoalInsert(Job.newBuilder()
//                        .setmCompositeGoalID(bigID)
//                        .setId(3)
//                        .setTitle("This is Job 3")
//                        .setTasksQuantity(20)
//                        .build())
//                HandlerThread.sleep(2000)
//
//                jobDAO.asyncSubGoalInsert(Job.newBuilder()
//                        .setmCompositeGoalID(bigID)
//                        .setId(4)
//                        .setTitle("This is Job 4")
//                        .setTasksQuantity(2)
//                        .build())
//            }
//        }
//
//        async(UI) {
//            bg{
//                HandlerThread.sleep(3000)
//                Log.d("Хуепес", jobDAO.getAll().size.toString())
//            }
//        }

//        goalsDataComponent
//                .getBigGoalDao()
//                .asyncInsert(BigGoal("Вставка в БД через расширяемую функцию"))

//        goalsDataComponent.getJobDao().asyncSubGoalInsert(Job.newBuilder()
//                .setmCompositeGoalID(1)
//                .setId(1)
//                .setTitle("This is Job 1")
//                .setTasksQuantity(20)
//                .build())

//        jobDAO.asyncSubGoalInsert(Job.newBuilder()
//                .setmCompositeGoalID(bigID)
//                .setId(6)
//                .setTitle("This is Job 6")
//                .setTasksQuantity(20)
//                .build())
    }


}
