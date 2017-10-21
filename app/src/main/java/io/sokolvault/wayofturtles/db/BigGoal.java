package io.sokolvault.wayofturtles.db;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "big_Goals")
public class BigGoal {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String mTitle;
    private String mDescription;
    private double mProgress = 0;
    private boolean isComplete = false;

    @Ignore
    public BigGoal(String mTitle, String mDescription) {
        this.mTitle = mTitle;
        this.mDescription = mDescription;
    }

    public BigGoal(int id, String mTitle, String mDescription) {
        this.id = id;
        this.mTitle = mTitle;
        this.mDescription = mDescription;
    }

    public String getTitle() {
        return mTitle;
    }
    public String getDescription() {
        return mDescription;
    }

    public int getId() {
        return id;
    }

    public void setProgress(double mProgress) {
        this.mProgress = mProgress;
    }

    public void setComplete(boolean complete) {
        isComplete = complete;
    }

    public double getProgress() {
        return mProgress;
    }

    public boolean isComplete() {
        return isComplete;
    }

}
