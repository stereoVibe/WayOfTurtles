package io.sokolvault.wayofturtles.data.db.model

import android.arch.persistence.room.*
import io.sokolvault.wayofturtles.model.HybridGoal
import io.sokolvault.wayofturtles.model.MonotypeGoal
import io.sokolvault.wayofturtles.model.xtensions.*
import io.sokolvault.wayofturtles.utils.AppTypeConverters
import io.sokolvault.wayofturtles.utils.Constants
import io.sokolvault.wayofturtles.utils.Constants.HYBRID_GOALS_TABLE_NAME
import io.sokolvault.wayofturtles.utils.Constants.HYBRID_GOAL_ID_COLUMN_NAME
import kotlin.collections.ArrayList

/* CompositeGoalRoom class is responsible for holding all Sub Goals, like a container.
*  Its function to fetching sub goals to appropriate Big Goal and holds overall progress,
*  according to complete/incomplete/inProgress status of sub goals */

@Entity(tableName = HYBRID_GOALS_TABLE_NAME,
        indices = arrayOf(Index(value = HYBRID_GOAL_ID_COLUMN_NAME,
                                unique = true)))
@TypeConverters(AppTypeConverters::class)
data class HybridGoalRoom constructor(override var title: String = "title")
    : HybridGoal() {

    @Ignore
    constructor(title: String, description: String) : this(title) { this.description = description }

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = HYBRID_GOAL_ID_COLUMN_NAME)
    override var id: Int = 0
    override var description: String = ""
    @ColumnInfo(name = Constants.COMPLETE_COLUMN_NAME)
    override var isComplete: Boolean = false
    @TypeConverters(AppTypeConverters::class)
    @ColumnInfo(name = Constants.GOAL_CATEGORY_COLUMN_NAME)
    override var goalCategory: Enum<GoalCategory> = GoalCategory.NONE

    override var progress: Double = 0.0
    @ColumnInfo(name = Constants.ALL_SUBGOALS_QUANTITY)
    override var allSubGoalsQuantity: Int = countAllSubGoals(this.createEmptyArray())
    @ColumnInfo(name = Constants.COMPLETD_SUBGOALS)
    override var completedSubGoalsQuantity: Int = countCompletedSubGoals(this.createEmptyArray())

    @Ignore
    override var subGoals: ArrayList<MonotypeGoal> = ArrayList()

    override fun addSubGoalToList(internable: MonotypeGoal) { subGoals.add(internable) }
    override fun addAllSubGoals(internables: List<MonotypeGoal>) { subGoals.addAll(internables) }

    override fun calculateProgress(): Double {
        var numOfCycles = 0
        val cycleShare: Double
        val modifier: Double
        val shareMap = LinkedHashMap<Int, Double>()
        var result = 0.0

//        TODO: Refactor this mess

        /*
        * Создание Map где key - id подцели, gradeModifier - соответствующая сложность
        * */
        val iDAndDiffGradeMap = defineDifficultyAndMap(subGoals)

        /*
        * Распределение целей (id) по группам (в виде List) соответствующим их сложности
        * */
//        val (normalList, easyList, hardList) = listOf<MutableList<Int>>()
        val normalList: MutableList<Int> = mutableListOf()
        val easyList: MutableList<Int> = mutableListOf()
        val hardList: MutableList<Int> = mutableListOf()
//        val easyList: MutableList<Int> = mutableListOf()
        iDAndDiffGradeMap.forEach { pair ->
            when (iDAndDiffGradeMap.getValue(pair.key)) {
                Difficulty.Grade.EASY -> easyList.add(pair.key)
                Difficulty.Grade.NORMAL -> normalList.add(pair.key)
                Difficulty.Grade.HARD -> hardList.add(pair.key)
            }
        }

        /*
        * Установка модификатора (множителя) сложности в зависимости от наличия градаций (групп)
        * в списке подцелей. Присваивание этого значения соответствующему кейсу
        * */
//        TODO: следование when идет по порядку, последовательно и поэтому попадает на первый враиан
//        val mod = when {
//            easyList.isEmpty() -> 1.1
//            hardList.isEmpty() -> 1.1
//            normalList.isEmpty() -> 1.2
//            else -> 1.0
//        }
//        modifier = mod

        modifier = if (easyList.isEmpty() && hardList.isEmpty()) 1.0
        else if (easyList.isEmpty() || hardList.isEmpty()) 1.1
        else if (normalList.isEmpty()) 1.2
        else 1.0

        /*
        * Вычисление общего количества "цикличных" целей. Одиночные цели (MonotypeGoal) считаются,
        * как один цикл
        * */
        when {
            subGoals.isNotEmpty() -> {
                subGoals.forEach { it ->
                    numOfCycles += when {
                        it.javaClass.isInstance(JobSubGoalRoom()) ->
                            (it as MonoСyclical).cycleQuantity
                        else -> 1
                    }
                }

                cycleShare = 100 / numOfCycles.toDouble()

                /*
                * Вычисление доли в прогрессе каждого цикла с учетом всех модификаторов и создание
                * коллекции этих значений для каждой подцели
                * */
                for (subGoalData in iDAndDiffGradeMap) {
                    val subGoal = subGoals.filter { it.id == subGoalData.key }[0]
                    val isCyclical: (MonotypeGoal) -> Boolean = { it.javaClass.isInstance(JobSubGoalRoom()) }
                    val gradeModifier = subGoalData.value.gradeModifier
                    val totalModifier: (Double) -> Double = { it * modifier }

                    when (isCyclical(subGoal)){
                        true -> shareMap.put(subGoalData.key,
                                (cycleShare * ((subGoal as MonoСyclical).cycleQuantity))
                                        * totalModifier(gradeModifier))
                        false -> shareMap.put(subGoalData.key,
                                cycleShare * totalModifier(gradeModifier))
                    }
                }

                subGoals.filter { it.isComplete }.forEach {
                    val diff: Double? = shareMap[it.id]
                    result += diff!!
                }
            }
        }

        return result
    }

    private fun createEmptyArray() = emptyList<MonotypeGoal>()

//    TODO: change access of defineDifficultyAndMap() to private
    fun defineDifficultyAndMap(subGoalsList: List<MonotypeGoal>): LinkedHashMap<Int, Difficulty.Grade>{
        val iDAndDiffGradeMap = LinkedHashMap<Int, Difficulty.Grade>()
        for (subGoal in subGoalsList) {
            val diff = subGoal as Difficulty
            iDAndDiffGradeMap.put(subGoal.id, diff.grade)
        }
        return iDAndDiffGradeMap
    }
}


//private fun Int.put(function: () -> Unit) {}
