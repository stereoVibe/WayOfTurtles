package io.sokolvault.wayofturtles

//import io.sokolvault.wayofturtles.di.DaggerGoalsDAOApplicationComponent
import android.arch.lifecycle.ViewModel
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.ArrayMap
import android.util.Log
import android.widget.Button
import io.sokolvault.wayofturtles.data.db.GoalsDatabase
import io.sokolvault.wayofturtles.data.db.model.CompositeGoalRoom
import io.sokolvault.wayofturtles.ui.BigGoalViewModel
import io.sokolvault.wayofturtles.utils.Constants
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg
import org.jetbrains.anko.find
import org.jetbrains.anko.longToast
import java.util.*

@Suppress("EXPERIMENTAL_FEATURE_WARNING")
class MainActivity : AppCompatActivity() {

    private lateinit var bigGoalViewModel: BigGoalViewModel
    private val arrayMap = ArrayMap<Class<out ViewModel>, ViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
//        applicationContext.deleteDatabase(Constants.DATABASE_NAME)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val loadButton: Button = find(R.id.loadButton)

        val db = GoalsDatabase.getInstance(this.applicationContext)
        var mdmDb = GoalsDatabase.getInMemoryInstance(this.applicationContext)

//        val goalsDataComponent = DaggerGoalsDAOApplicationComponent.builder()
//                .contextModule(ContextModule(this))
//                .build()

//        BigGoalRepositoryImpl.injectDbInstance(goalsDataComponent.getDbInstance())

//        arrayMap.put(BigGoalViewModel::class.java, BigGoalViewModel())
//        bigGoalViewModel = ViewModelProviders
//                .of(this, ViewModelFactory(arrayMap))
//                .get(BigGoalViewModel::class.java)

//        val bigGoal = BigGoal(3, "Заголовок")

//        bigGoalViewModel.singleGoal.observe(this, Observer {
//            when(DbOps.status){
//                Status.SUCCESS -> {
//                    longToast("Заебись! Че-то получилось")
//                    Log.d(this::class.simpleName, it?.title)
//                    DbOps.status = Status.IDLE
//                }
//                Status.LOADING -> longToast("Загрузка говорят началась")
//                Status.ERROR -> longToast("Ебись оно конем!")
//                Status.IDLE -> longToast("Стою, жду")
//                null -> longToast("Ты как сюда попал, Вася?!?!")
//            }
//        })

//        toast(DbOps.checkStatus().toString

        loadButton.setOnClickListener({ it ->
            async(UI) {

                val toNat:     (Int) -> Int     = { x -> x * -1}
                val isNat:     (Int) -> Boolean = { x -> x < 0}
                val checkRand: (Int) -> Int     = { it -> when(isNat(it)) {
                                true -> toNat(it)
                                else -> it} }

                val data = bg {
                    val seed = Random().nextGaussian() * 12.6
                    val rand = checkRand(seed.toInt())
                    val bigGoal = CompositeGoalRoom("Заголовок $rand")
//                    goalsDataComponent.getDbInstance().bigGoalDAO().insertBigGoal(bigGoal)
                    mdmDb.bigGoalDAO().insertBigGoal(bigGoal)
                }
                longToast("Че-то получилось ${data.await()}")
            }
//            bigGoalViewModel.createNewGoal(bigGoal)
//            bigGoalViewModel.getGoalById(bigGoal.id)
            mdmDb = GoalsDatabase.getInMemoryInstance(this@MainActivity.applicationContext)
            val status = mdmDb.isOpen
            Log.d(mdmDb::class.simpleName, "База открыта? $status")
        })

//        val bigGoalDAO = goalsDataComponent.getBigGoalDao()
//        bigGoalDAO.asyncInsert(BigGoalEntity("Вставка в БД через расширяемую функцию"))
    }

    override fun onStop() {
        super.onStop()
        GoalsDatabase.getInstance(applicationContext).close()
        val status: Boolean = applicationContext.deleteDatabase(Constants.DATABASE_NAME)
        Log.d(this::class.simpleName, "Сработал On Stop. $status.")
    }
}
