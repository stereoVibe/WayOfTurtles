package io.sokolvault.wayofturtles

//import io.sokolvault.wayofturtles.di.DaggerGoalsDAOApplicationComponent
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.ArrayMap
import android.util.Log
import android.widget.Button
import io.sokolvault.wayofturtles.carriers.DataCompositeGoal
import io.sokolvault.wayofturtles.repositories.presence.PresenceRepositoryData
import io.sokolvault.wayofturtles.ui.BigGoalViewModel
import io.sokolvault.wayofturtles.ui.ViewModelFactory
import io.sokolvault.wayofturtles.utils.Constants
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg
import org.jetbrains.anko.find
import org.jetbrains.anko.longToast
import java.util.*
import javax.inject.Inject

@Suppress("EXPERIMENTAL_FEATURE_WARNING")
class MainActivity : AppCompatActivity() {

//    @Inject
//    lateinit var dbase: GoalsDatabase

    lateinit var bigGoalViewModel: BigGoalViewModel

    @Inject
    lateinit var repository: PresenceRepositoryData

    private val arrayMap = ArrayMap<Class<out ViewModel>, ViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
//        applicationContext.deleteDatabase(Constants.DATABASE_NAME)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        App.getComponent().injectMainActivity(this)
        val loadButton: Button = find(R.id.loadButton)

//        val db = GoalsDatabase.getInstance(this.applicationContext)
//        var mdmDb = GoalsDatabase.getInMemoryInstance(this.applicationContext)

//        val goalsDataComponent = DaggerGoalsDAOComponent.builder()
//                .contextModule(ContextModule(this))
//                .build()
//        goalsDataComponent.injectGoalsDAOComponent(this)

//        val turtlesWay = TurtlesWayApp.get(this)


//        BigGoalRepositoryImpl.injectDbInstance(goalsDataComponent.provideDbInstance())


        arrayMap.put(BigGoalViewModel::class.java, BigGoalViewModel(repository))
        bigGoalViewModel = ViewModelProviders
                .of(this, ViewModelFactory(arrayMap))
                .get(BigGoalViewModel::class.java)

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
                    val bigGoal = DataCompositeGoal("Заголовок + $rand")
                    Log.d("Цель", "${bigGoal.title}")
                    bigGoalViewModel.createGoal(bigGoal)
//                    goalsDataComponent.provideDbInstance().bigGoalDAO().insertBigGoal(bigGoal)
//                    mdmDb.bigGoalDAO().insertBigGoal(bigGoal)
                }
                longToast("Че-то получилось ${data.await()}")
            }
//            bigGoalViewModel.createNewGoal(bigGoal)
//            bigGoalViewModel.getGoalById(bigGoal.id)
//            mdmDb = GoalsDatabase.getInMemoryInstance(this@MainActivity.applicationContext)
//            val status = mdmDb.isOpen
//            Log.d(mdmDb::class.simpleName, "База открыта? $status")
        })

//        val bigGoalDAO = goalsDataComponent.provideBigGoalDao()
//        bigGoalDAO.asyncInsert(BigGoalEntity("Вставка в БД через расширяемую функцию"))

//        while (true) {
//            val mountainH: Int = Random().nextInt()
//            val findClosestMax: (Int, Array<Int>) -> (Int) = { x, y -> if (y[x] > y[x + 1]) x else x + 1 }
//            var heights: Array<Int> = Array(8, { _ -> 0 })
//            val inBound: (Int) -> (Int) = { i ->  if(i < 8) i + 1 else i }
//
//            heights.max()
//
//            val refreshMax = fun(indx: Int, max: Int, heights: Array<Int>) :Int{
//                val localMax: Int
//                return if (heights[indx] > max) {
//                    localMax = heights[indx]
//                    heights[indx] = 0
//                    localMax
//                } else max
//            }
//
//            val locateMax = fun(index: Int, heights: Array<Int>): Int {
//                var max : Int = 0
//                for (i in 0 until 8) {
//                    max = refreshMax(i, max, heights)
//                    if ( > max) return i else index
//                }
//            }
//
//
////            val locateMax: (Int, Int, Array<Int>) -> (Int) = { i, max, arr ->
////                    if (arr[i] > max) arr[i] else max
////                    }
//
//            for (i in heights.iterator()){
//
//            }
//
//            for (i in 0 until 8){
//                heights[i] = mountainH
//            }
////            val heights = arrayOf()
//
//
//            // Write an action using println()
//
//            // To debug: System.err.println("Debug messages...");
//            for (i in 0 until 8) {  // The index of the mountain to fire on.
//                "${heights.size}"
//                println(i)
//            }
//        }

    }

    override fun onStop() {
        super.onStop()
//        GoalsDatabase.getInstance(applicationContext).close()
        val status: Boolean = applicationContext.deleteDatabase(Constants.DATABASE_NAME)
        Log.d(this::class.simpleName, "Сработал On Stop. $status.")
    }

}
