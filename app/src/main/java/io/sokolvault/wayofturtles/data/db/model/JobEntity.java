package io.sokolvault.wayofturtles.data.db.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import io.sokolvault.wayofturtles.utils.AppTypeConverters;
import io.sokolvault.wayofturtles.model.xtensions.GoalCategory;
import io.sokolvault.wayofturtles.model.base.Goal;
import io.sokolvault.wayofturtles.model.complex.SubGoal;

import static android.arch.persistence.room.ForeignKey.CASCADE;

/* One of SubGoal type class and sub goals in matter of abstraction «Goals -> Sub Goals».
Main feature is to implement continuous (extended) tasks, something that needs repeatable actions (
playing guitar, gym, etc.)
* */

@Entity(tableName = "jobs_sub_goals", indices = {@Index(value = "composite_goal_id", unique = true)},
        foreignKeys = @ForeignKey(entity = BigGoalEntity.class,
                parentColumns = "big_goal_id",
                childColumns = "composite_goal_id",
                onDelete = CASCADE,
                onUpdate = CASCADE, deferred = true))
//@Entity(tableName = "sub_goals")
@TypeConverters(AppTypeConverters.class)
public class JobEntity extends Goal implements SubGoal {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "goal_category")
    private Enum<GoalCategory> categoryEnum;

    @ColumnInfo(name = "composite_goal_id")
    private int mCompositeGoalID;
    private int mTasksQuantity;
    private int mCompletedTasksQuantity;

    public JobEntity(int id, String title, int mCompositeGoalID){
        super(title);
        this.id = id;
        this.mCompositeGoalID = mCompositeGoalID;
    }

    @Ignore
    public JobEntity(int id, int mCompositeGoalID, String title, int mTasksQuantity) {
        super(title);
        this.id = id;
        this.mCompositeGoalID = mCompositeGoalID;
        this.mTasksQuantity = mTasksQuantity;
    }

    @Ignore
    private JobEntity(Builder builder) {
        super(builder.title);
        setId(builder.id);
        setDescription(builder.description);
        this.mCompositeGoalID = builder.compositeGoalID;
        this.mTasksQuantity = builder.tasksQuantity;
    }

    public Enum<GoalCategory> getCategoryEnum() {
        return categoryEnum;
    }

    public void setCategoryEnum(Enum<GoalCategory> categoryEnum) {
        this.categoryEnum = categoryEnum;
    }

    @Override
    public int getCompositeGoalId() {
        return mCompositeGoalID;
    }

    public void setCompositeGoalID(int mCompositeGoalID) {
        this.mCompositeGoalID = mCompositeGoalID;
    }

    public int getTasksQuantity() {
        return mTasksQuantity;
    }

    public void setTasksQuantity(int mTasksQuantity) {
        this.mTasksQuantity = mTasksQuantity;
    }

    public int getCompletedTasksQuantity() {
        return mCompletedTasksQuantity;
    }

    public void setCompletedTasksQuantity(int mCompletedTasksQuantity) {
        this.mCompletedTasksQuantity = mCompletedTasksQuantity;
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Ignore
    public static Builder newBuilder() {
        return new Builder();
    }

    public void setId(int i) {
        this.id = i;
    }


    public static final class Builder {
        private int id;
        private String title;
        private String description;
        private int compositeGoalID;
        private int tasksQuantity;

        private Builder() {
        }

        public Builder setId(int id) {
            this.id = id;
            return this;
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder setCompositeGoalID(int id) {
            this.compositeGoalID = id;
            return this;
        }

        public Builder setTasksQuantity(int quantity){
            this.tasksQuantity = quantity;
            return this;
        }

        public JobEntity build() {
            return new JobEntity(this);
        }
    }
}
