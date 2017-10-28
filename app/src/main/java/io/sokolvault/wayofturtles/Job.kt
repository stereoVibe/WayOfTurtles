//package io.sokolvault.wayofturtles
//
//import android.arch.persistence.room.Entity
//import android.arch.persistence.room.PrimaryKey
//import io.sokolvault.wayofturtles.db.BigGoal
//
//@Entity(tableName = "Job_SubGoals")
//class Job(title: String, bigGoal: BigGoal): AbstractGoal(title),
//        SubGoal{
//
//    @PrimaryKey(autoGenerate = true)
//    override var id: Int?
//        get() = id
//        set(value) {id = value}
//}