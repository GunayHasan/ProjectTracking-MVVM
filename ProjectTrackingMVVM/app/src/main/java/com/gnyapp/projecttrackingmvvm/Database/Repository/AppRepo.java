package com.gnyapp.projecttrackingmvvm.Database.Repository;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.gnyapp.projecttrackingmvvm.Database.AppDataBase;
import com.gnyapp.projecttrackingmvvm.Database.ProjectDao;
import com.gnyapp.projecttrackingmvvm.ProjectModel;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class AppRepo {

    private AppDataBase appDataBase;
    private Executor executor = Executors.newSingleThreadExecutor();

    public AppRepo(Context context) {
        appDataBase = AppDataBase.getInstance(context);
    }

    public void insertProject(ProjectModel projectModel) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                appDataBase.projectDao().insertProject(projectModel);
            }
        });
    }

    public void updateProject(ProjectModel projectModel) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                appDataBase.projectDao().updateProject(projectModel);
            }
        });
    }

    public void deleteProject(ProjectModel projectModel) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                appDataBase.projectDao().deleteProject(projectModel);
            }
        });
    }

    public LiveData<List<ProjectModel>> getAllProjects() throws ExecutionException, InterruptedException {
        return appDataBase.projectDao().getAllProjeects();
    }
}
