package io.sokolvault.wayofturtles.db.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

import io.sokolvault.wayofturtles.model.AbstractGoal;
import io.sokolvault.wayofturtles.model.SubGoal;

import static android.arch.persistence.room.ForeignKey.CASCADE;

/* One of SubGoal type class and sub goals in matter of abstraction «Goals -> Sub Goals».
Main feature is to implement continuous (extended) tasks, something that needs repeatable actions (
playing guitar, gym, etc.)
* */

@Entity(tableName = "sub_goals", indices = {@Index(value = "composite_goal_id", unique = true)},
        foreignKeys = @ForeignKey(entity = BigGoalEntity.class,
                parentColumns = "big_goal_id",
                childColumns = "composite_goal_id",
                onDelete = CASCADE,
                onUpdate = CASCADE, deferred = true))
//@Entity(tableName = "sub_goals")
public class JobEntity extends AbstractGoal implements SubGoal {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "composite_goal_id")
    private int compositeGoalID;
    private int mTasksQuantity;
    private int mCompletedTasksQuantity;

    public JobEntity(int id, String title, int compositeGoalID){
        super(title);
        this.id = id;
        this.compositeGoalID = compositeGoalID;
    }

    @Ignore
    public JobEntity(int id, int compositeGoalID, String title, int mTasksQuantity) {
        super(title);
        this.id = id;
        this.compositeGoalID = compositeGoalID;
        this.mTasksQuantity = mTasksQuantity;
    }

    @Ignore
    private JobEntity(Builder builder) {
        super(builder.title);
        setId(builder.id);
        setDescription(builder.description);
        this.compositeGoalID = builder.compositeGoalID;
        this.mTasksQuantity = builder.tasksQuantity;
    }


    public int getCompositeGoalID() {
        return compositeGoalID;
    }

    public void setCompositeGoalID(int compositeGoalID) {
        this.compositeGoalID = compositeGoalID;
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

    @Override
    public void setId(int i) {
        this.id = i;
    }

    @Override
    public void fetch() {

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
