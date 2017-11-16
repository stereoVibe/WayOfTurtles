package io.sokolvault.wayofturtles.utils


object Constants {

//        Database name
        const val DATABASE_NAME = "goals"

//        Tables names
        const val COMPOSITE_GOALS_TABLE_NAME = "composite_goals"
        const val JOB_SUBGOALS_TABLE_NAME = "job_subgoals"
        const val TASK_SUBGOALS_TABLE_NAME = "task_subgoals"
        const val JOB_GOALS_TABLE_NAME = "job_goals"
        const val TASK_GOALS_TABLE_NAME = "task_goals"

//        Db Column names
        const val COMPOSITE_GOAL_ID_COLUMN_NAME = "id"
        const val FOREIGN_KEY_COMPOSITE_GOAL_ID_COLUMN = "composite_goal_id"
        const val COMPLETE_COLUMN_NAME = "completed"
        const val GOAL_CATEGORY_COLUMN_NAME = "goal_category"
        const val SUBGOALS_COLUMN_NAME = "sub_goals"
        const val JOBS_QUANTITY_COLUMN_NAME = "jobs_quantity"
}