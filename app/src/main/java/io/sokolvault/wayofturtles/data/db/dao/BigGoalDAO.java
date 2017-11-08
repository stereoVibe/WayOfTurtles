package io.sokolvault.wayofturtles.data.db.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.TypeConverters;
import android.arch.persistence.room.Update;

import java.util.List;

import io.sokolvault.wayofturtles.model.complex.CompositeGoal;
import io.sokolvault.wayofturtles.utils.AppTypeConverters;
import io.sokolvault.wayofturtles.data.db.model.BigGoalEntity;

@Dao
@TypeConverters(AppTypeConverters.class)
public interface BigGoalDAO {

    @Query("SELECT * FROM big_goals")
    List<BigGoalEntity> getAll();

    @Query("SELECT * FROM big_goals WHERE big_goal_id = :id")
    LiveData<CompositeGoal> getBigGoalById(int id);

    @Insert
    Long insertBigGoal(BigGoalEntity bigGoal);

    @Update
    void updateBigGoal(BigGoalEntity bigGoalEntity);

    @Delete
    void delete(BigGoalEntity bigGoal);

}

