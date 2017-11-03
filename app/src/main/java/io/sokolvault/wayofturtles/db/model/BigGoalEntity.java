package io.sokolvault.wayofturtles.db.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.VisibleForTesting;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

import io.sokolvault.wayofturtles.model.AbstractGoal;
import io.sokolvault.wayofturtles.model.SubGoal;

/* BigGoal class is responsible for holding all Sub Goals, like a container.
*  Its function to fetching sub goals to appropriate Big Goal and holds overall progress,
*  according to complete/incomplete/inProgress status of sub goals */

@Entity(tableName = "big_goals", indices = {@Index(value = "big_goal_id", unique = true)} )
public class BigGoalEntity extends AbstractGoal {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "big_goal_id")
    private int id;

    @Ignore
    private ArrayList<SubGoal> mSubGoalsList;

    @Ignore
    public BigGoalEntity(String title, String description) {
        super(title);
        super.setDescription(description);
    }

    public BigGoalEntity(String title){
        super(title);
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public void setId(int i) {
        this.id = i;
    }

//    @Ignore
//    @VisibleForTesting
//    public BigGoalEntity(int id, String title, String description) {
//        super(title);
//        this.id = id;
//        setDescription(description);
//    }

//    @Override
//    public int getId() {
//        return this.id;
//    }
//
//    @Override
//    public void setId(int i) {
//        this.id = i;
//    }
}
