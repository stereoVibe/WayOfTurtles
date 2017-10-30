package io.sokolvault.wayofturtles.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

/* BigGoal class is responsible for holding all Sub Goals, like a container.
*  Its function to fetching sub goals to appropriate Big Goal and holds overall progress,
*  according to complete/incomplete/inProgress status of sub goals */

@Entity(tableName = "big_goals", indices = {@Index(value = "big_goal_id", unique = true)} )
public class BigGoal extends AbstractGoal implements CompositeGoal{

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "big_goal_id")
    private int id;

    @Ignore
    private ArrayList<SubGoal> mSubGoalsList;

    @Ignore
    public BigGoal(String title){
        super(title);
    }

    @Ignore
    public BigGoal(String title, String description) {
        super(title);
        super.setDescription(description);
    }

    public BigGoal(int id, String title) {
        super(title);
        this.id = id;
    }

    @Override
    public int getId() {
        return this.id;
    }

    @NotNull
    @Override
    public ArrayList<SubGoal<CompositeGoal>> getSubGoalsList() {
        return null;
    }

    @Override
    public void setSubGoalsList(@NotNull ArrayList<SubGoal<CompositeGoal>> arrayList) {

    }

    @Override
    public void setId(int i) {
    }
}
