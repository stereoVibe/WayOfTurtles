package io.sokolvault.wayofturtles

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.ArrayMap
import io.sokolvault.wayofturtles.data.db.dao.BigGoalDAO

import io.sokolvault.wayofturtles.utils.DbOps.Companion.asyncInsert
import io.sokolvault.wayofturtles.utils.DbOps.Companion.asyncSubGoalInsert
import io.sokolvault.wayofturtles.data.db.model.BigGoalEntity
import io.sokolvault.wayofturtles.data.db.model.JobEntity
import io.sokolvault.wayofturtles.domain.model.SubGoal
import io.sokolvault.wayofturtles.ui.BigGoalViewModel
import io.sokolvault.wayofturtles.ui.ViewModelFactory
import io.sokolvault.wayofturtles.utils.Status
import javax.inject.Inject

@Suppress("EXPERIMENTAL_FEATURE_WARNING")
class MainActivity : AppCompatActivity() {

    private lateinit var bigGoalViewModel: BigGoalViewModel
    private val arrayMap = ArrayMap<Class<out ViewModel>, ViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        applicationContext.deleteDatabase("goals")
        setContentView(R.layout.activity_main)

        val goalsDataComponent = DaggerGoalsDAOApplicationComponent.builder()
                .contextModule(ContextModule(this))
                .build()

        arrayMap.put(BigGoalViewModel::class.java, BigGoalViewModel())
        bigGoalViewModel = ViewModelProviders.of(this, ViewModelFactory(arrayMap)).get(BigGoalViewModel::class.java)


        bigGoalViewModel.goalsList.observe(this, Observer {
            when(it?.status){
                Status.SUCCESS -> {
                }
                Status.LOADING -> {}
                Status.ERROR -> {}
                Status.IDLE -> TODO()
                null -> TODO()
            }
        })

//        val bigGoalDAO = goalsDataComponent.getBigGoalDao()
//        bigGoalDAO.asyncInsert(BigGoalEntity("Вставка в БД через расширяемую функцию"))
//
//        val jobDAO1 = goalsDataComponent.getJobDao()
//
//        jobDAO1.asyncSubGoalInsert(JobEntity.newBuilder()
//                .setCompositeGoalID(3)
//                .setId(14)
//                .setTitle("This is Job 14")
//                .setTasksQuantity(5)
//                .build())

//        async(UI) {
//            bg{
//                Log.d("Хуепес", jobDAO1.getAll().size.toString())
//            }
//        }
//
    }


}
