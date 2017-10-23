package io.sokolvault.wayofturtles;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import io.sokolvault.wayofturtles.db.BigGoal;

@Entity(tableName = "Job_SubGoals")
public class Job extends AbstractGoal implements SubGoal<BigGoal> {

//    private String mTitle = super.getTitle();
//    private String mDescription = super.getDescription();
    @PrimaryKey(autoGenerate = true)
    private Integer id;
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

    @Override
    public void fetch(BigGoal bigGoal) {

    }

    @Ignore
    public static Builder newBuilder() {
        return new Builder();
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
