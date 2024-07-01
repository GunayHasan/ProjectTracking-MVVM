package com.gnyapp.projecttrackingmvvm.Database;

import android.content.Context;

import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.gnyapp.projecttrackingmvvm.ProjectModel;

@Database(entities = {ProjectModel.class}, exportSchema = false, version = 1)
public abstract class AppDataBase extends RoomDatabase {

    public static final String DATABASE_NAME = "project_database.db";
    public static AppDataBase instance;
    private static final Object LOCK =  new Object();
    public abstract ProjectDao projectDao();

    public static AppDataBase getInstance(Context context) {
        if(instance == null) {
            synchronized (LOCK){
                if(instance == null) {
                    instance = Room.databaseBuilder(context.getApplicationContext(),
                            AppDataBase.class, DATABASE_NAME)
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }
        return instance;
    }

}
