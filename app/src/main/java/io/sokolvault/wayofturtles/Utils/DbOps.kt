package io.sokolvault.wayofturtles.Utils

import android.arch.persistence.room.Dao
import io.sokolvault.wayofturtles.db.BigGoalDAO
import io.sokolvault.wayofturtles.model.BigGoal
import java.util.ArrayList

class DbOps {

    companion object {
         fun insertBigGoal(LOCK: Any, bigGoal: BigGoal, bigGoalDAO: BigGoalDAO){
             Thread {
                 synchronized(LOCK) {
                        bigGoalDAO.insertBigGoal(bigGoal)
//                    TODO: This block below of Java concurrent way should be replaced by concurrent Kotlin library
                        (LOCK as java.lang.Object).notify()
                 }
             }.start()
         }
    }

}