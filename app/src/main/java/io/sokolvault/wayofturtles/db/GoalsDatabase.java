package io.sokolvault.wayofturtles.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.util.Log;

import io.sokolvault.wayofturtles.model.BigGoal;
import io.sokolvault.wayofturtles.model.Job;


@Database(entities = {BigGoal.class, Job.class}, version = 1)
public abstract class GoalsDatabase extends RoomDatabase{

    public static final String DATABASE_NAME = "goals";
    private static final String LOG_TAG = GoalsDatabase.class.getSimpleName();

    private static final Object LOCK = new Object();
    private static GoalsDatabase sInstance;

    public static GoalsDatabase getInstance (Context context) {
        Log.d(LOG_TAG, "Getting the instance of database");
        if (sInstance == null) {
            synchronized (LOCK) {
                sInstance = Room.databaseBuilder(context.getApplicationContext(),
                        GoalsDatabase.class, GoalsDatabase.DATABASE_NAME).build();
                Log.d(LOG_TAG, "New database creating");
            }
        }
        return sInstance;
    }

    public void clear(){
        sInstance = null;
    }
    public abstract BigGoalDAO bigGoalDAO();

}
