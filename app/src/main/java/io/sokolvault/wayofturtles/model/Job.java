package io.sokolvault.wayofturtles.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import org.jetbrains.annotations.Nullable;

/* One of SubGoal type class and sub goals in matter of abstraction «Goals -> Sub Goals».
Main feature is to implement continuous (extended) tasks, something that needs repeatable actions (
playing guitar, gym, etc.)
* */

//@Entity(tableName = "sub_Goals", foreignKeys = @ForeignKey(entity = BigGoal.class,
//                                                           parentColumns = "id",
//                                                           childColumns = "composite_goal_ID"))
@Entity(tableName = "sub_Goals")
public class Job extends AbstractGoal implements SubGoal<BigGoal> {

//    private String mTitle = super.getTitle();
//    private String mDescription = super.getDescription();
    @PrimaryKey(autoGenerate = true)
    private Integer id;
    @ColumnInfo(name = "composite_goal_ID")
    private int compositeGoalID;
    private int mTasksQuantity;
    private int mCompletedTasksQuantity;

    public Job( int id, String title) {
        super(title);
        this.id = id;
    }

    @Ignore
    private Job(Builder builder) {
        super(builder.title);
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
    public void setId(@Nullable Integer integer) {
        this.id = integer;
    }

    @Nullable
    @Override
    public Integer getId() {
        return this.id;
    }

    @Ignore
    public static Builder newBuilder() {
        return new Builder();
    }

    @Override
    public void fetch(BigGoal bigGoal) {

    }

    public static final class Builder {
        private String title;
        private String description;
        private int compositeGoalID;
        private int tasksQuantity;

        private Builder() {
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

        public Job build() {
            return new Job(this);
        }
    }
}
