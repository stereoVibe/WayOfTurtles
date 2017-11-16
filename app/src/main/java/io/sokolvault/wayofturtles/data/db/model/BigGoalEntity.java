//package io.sokolvault.wayofturtles.data.db.model;
//
//import android.arch.persistence.room.ColumnInfo;
//import android.arch.persistence.room.Entity;
//import android.arch.persistence.room.Ignore;
//import android.arch.persistence.room.Index;
//import android.arch.persistence.room.PrimaryKey;
//import android.arch.persistence.room.TypeConverters;
//
//import java.util.Set;
//
//import io.sokolvault.wayofturtles.utils.AppTypeConverters;
//import io.sokolvault.wayofturtles.model.xtensions.GoalCategory;
//import io.sokolvault.wayofturtles.model.Goal;
//import io.sokolvault.wayofturtles.model.Internable;
//
///* BigGoal class is responsible for holding all Sub Goals, like a container.
//*  Its function to fetching sub goals to appropriate Big Goal and holds overall progress,
//*  according to complete/incomplete/inProgress status of sub goals */
//
//@Entity(tableName = "big_goals", indices = {@Index(value = "big_goal_id", unique = true)} )
//@TypeConverters(AppTypeConverters.class)
//public class BigGoalEntity extends Goal {
//
//    @PrimaryKey(autoGenerate = true)
//    @ColumnInfo(name = "big_goal_id")
//    private int id;
//
//    @ColumnInfo(name = "goal_category")
//    private Enum<GoalCategory> categoryEnum;
//
//    @Ignore
//    private Set<Internable> mSubGoalsList;
//
//    @Ignore
//    public BigGoalEntity(String title, String description) {
//        super(title);
//        super.setDescription(description);
//    }
//
//    public BigGoalEntity(String title){
//        super(title);
//    }
//
////    @Ignore
////    public BigGoalEntity(int id, @NotNull String title, @NotNull String description) {
////
////    }
//
//    @Override
//    public int getId() {
//        return this.id;
//    }
//
//    public void setId(int i) {
//        this.id = i;
//    }
//
//    public Enum<GoalCategory> getCategoryEnum() {
//        return categoryEnum;
//    }
//
//    public void setCategoryEnum(Enum<GoalCategory> categoryEnum) {
//        this.categoryEnum = categoryEnum;
//    }
//
//    public Set<Internable> getSubGoalsList() {
//        return mSubGoalsList;
//    }
//
//    public void setSubGoalsList(Set<Internable> mSubGoalsList) {
//        this.mSubGoalsList = mSubGoalsList;
//    }
//
//    //    @Ignore
////    @VisibleForTesting
////    public BigGoalEntity(int id, String title, String description) {
////        super(title);
////        this.id = id;
////        setDescription(description);
////    }
//
////    @Override
////    public int getId() {
////        return this.id;
////    }
////
////    @Override
////    public void setId(int i) {
////        this.id = i;
////    }
//}
