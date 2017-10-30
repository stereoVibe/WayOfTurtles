package io.sokolvault.wayofturtles.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import io.sokolvault.wayofturtles.model.BigGoal;

@Dao
public interface BigGoalDAO {

    @Query("SELECT * FROM big_goals")
    List<BigGoal> getAll();

    @Query("SELECT * FROM big_goals WHERE big_goal_id = :id")
    BigGoal getBigGoalById(int id);

    @Insert
    void insertBigGoal(BigGoal bigGoal);

    @Delete
    void delete(BigGoal bigGoal);

}

