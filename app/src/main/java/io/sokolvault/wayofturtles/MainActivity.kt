package io.sokolvault.wayofturtles

//import io.sokolvault.wayofturtles.di.DaggerGoalsDAOApplicationComponent
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.ArrayMap
import android.util.Log
import android.widget.Button
import io.sokolvault.wayofturtles.data.db.GoalsDatabase
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

        arrayMap.put(BigGoalViewModel::class.java, BigGoalViewModel(repository))
        bigGoalViewModel = ViewModelProviders
                .of(this, ViewModelFactory(arrayMap))
                .get(BigGoalViewModel::class.java)

        loadButton.setOnClickListener({ it ->
            async(UI) {

                val toNat:     (Int) -> Int     = { x -> x * -1}
                val isNat:     (Int) -> Boolean = { x -> x < 0}
                val checkRand: (Int) -> Int     = { it -> when(isNat(it)) {
                                true -> toNat(it)
                                else -> it} }

                val data = bg {
//                    val seed = Random().nextGaussian() * 12.6
//                    val rand = checkRand(seed.toInt())
//                    val bigGoal =
//                    Log.d("Цель", bigGoal.title)
//                    bigGoalViewModel.createGoal(bigGoal)
//                    goalsDataComponent.provideDbInstance().compositeGoalDAO().insertBigGoal(bigGoal)
//                    mdmDb.compositeGoalDAO().insertBigGoal(bigGoal)
                }
                longToast("Че-то получилось ${data.await()}")
            }
        })

    }

    override fun onStop() {
        super.onStop()
        GoalsDatabase.getDbInstance(applicationContext).close()
        val status: Boolean = applicationContext.deleteDatabase(Constants.DATABASE_NAME)
        Log.d(this::class.simpleName, "Сработал On Stop. $status.")
    }

}
