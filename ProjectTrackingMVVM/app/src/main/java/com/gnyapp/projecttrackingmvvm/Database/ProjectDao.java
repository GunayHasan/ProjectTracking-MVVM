package com.gnyapp.projecttrackingmvvm.Database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.gnyapp.projecttrackingmvvm.ProjectModel;

import java.util.List;

@Dao
public interface ProjectDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertProject(ProjectModel projectModel);

    @Update
    void updateProject(ProjectModel projectModel);

    @Delete
    void deleteProject(ProjectModel projectModel);

    @Query("SELECT * FROM project")
    LiveData<List<ProjectModel>> getAllProjeects();

    @Query("SELECT * FROM project WHERE pId=:id")
    List<ProjectModel> getProjeect(int id);



}
