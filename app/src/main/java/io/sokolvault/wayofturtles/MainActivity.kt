package io.sokolvault.wayofturtles

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.ArrayMap
import android.util.Log
import android.widget.Button

import io.sokolvault.wayofturtles.dto.DataBigGoal
import io.sokolvault.wayofturtles.repositories.BigGoalRepositoryImpl
import io.sokolvault.wayofturtles.ui.BigGoalViewModel
import io.sokolvault.wayofturtles.ui.ViewModelFactory
import io.sokolvault.wayofturtles.utils.DbOps
import io.sokolvault.wayofturtles.utils.Status
import org.jetbrains.anko.find
import org.jetbrains.anko.longToast

@Suppress("EXPERIMENTAL_FEATURE_WARNING")
class MainActivity : AppCompatActivity() {

    private lateinit var bigGoalViewModel: BigGoalViewModel
    private val arrayMap = ArrayMap<Class<out ViewModel>, ViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        applicationContext.deleteDatabase("goals")
        setContentView(R.layout.activity_main)

        val loadButton: Button = find(R.id.loadButton)

        val goalsDataComponent = DaggerGoalsDAOApplicationComponent.builder()
                .contextModule(ContextModule(this))
                .build()

        BigGoalRepositoryImpl.injectDbInstance(goalsDataComponent.getDbInstance())

        arrayMap.put(BigGoalViewModel::class.java, BigGoalViewModel())
        bigGoalViewModel = ViewModelProviders
                .of(this, ViewModelFactory(arrayMap))
                .get(BigGoalViewModel::class.java)

//        val bigGoal = BigGoal(3, "Заголовок")
        val bigGoal = DataBigGoal(3, "Заголовок")

        bigGoalViewModel.singleGoal.observe(this, Observer {

            when(DbOps.status){
                Status.SUCCESS -> {
                    longToast("Заебись! Че-то получилось")
                    Log.d(this::class.simpleName, it?.title)
                    DbOps.status = Status.IDLE
                }
                Status.LOADING -> longToast("Загрузка говорят началась")
                Status.ERROR -> longToast("Ебись оно конем!")
                Status.IDLE -> longToast("Стою, жду")
                null -> longToast("Ты как сюда попал, Вася?!?!")
            }
        })

//        toast(DbOps.checkStatus().toString())
        loadButton.setOnClickListener({
            bigGoalViewModel.createNewGoal(bigGoal)
            bigGoalViewModel.getGoalById(bigGoal.id)
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
    }

    override fun onStop() {
        super.onStop()
        applicationContext.deleteDatabase("goals")
    }
}
