package io.sokolvault.wayofturtles.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import io.sokolvault.wayofturtles.db.model.BigGoalEntity;

@Dao
public interface BigGoalDAO {

    @Query("SELECT * FROM big_goals")
    List<BigGoalEntity> getAll();

    @Query("SELECT * FROM big_goals WHERE big_goal_id = :id")
    BigGoalEntity getBigGoalById(int id);

//    @Insert
//    void insertBigGoal(BigGoal bigGoal);

    @Insert
    Long insertBigGoal(BigGoalEntity bigGoal);

    @Update
    void updateBigGoal(BigGoalEntity bigGoalEntity);

    @Delete
    void delete(BigGoalEntity bigGoal);

}

