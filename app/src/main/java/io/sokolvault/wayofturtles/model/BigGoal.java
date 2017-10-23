package io.sokolvault.wayofturtles.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

@Entity(tableName = "big_Goals")
public class BigGoal extends AbstractGoal implements CompositeGoal{

    @PrimaryKey(autoGenerate = true)
    private int id;

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

    @Nullable
    @Override
    public Integer getId() {
        return this.id;
    }

    @Override
    public void setId(@Nullable Integer integer) {
        this.id = integer;
    }


    @NotNull
    @Override
    public ArrayList<SubGoal> getSubGoalsList() {
        return null;
    }

    @Override
    public void setSubGoalsList(@NotNull ArrayList arrayList) {

    }
}
