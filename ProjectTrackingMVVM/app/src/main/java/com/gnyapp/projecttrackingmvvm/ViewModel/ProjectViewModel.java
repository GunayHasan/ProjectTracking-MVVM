package com.gnyapp.projecttrackingmvvm.ViewModel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.gnyapp.projecttrackingmvvm.ProjectModel;
import com.gnyapp.projecttrackingmvvm.Database.Repository.AppRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class ProjectViewModel extends AndroidViewModel {

    private AppRepo appRepo;

    public ProjectViewModel(Application application) {
        super(application);
        appRepo = new AppRepo(application);
    }

    public void insertProject(ProjectModel projectModel) {
        appRepo.insertProject(projectModel);
    }

    public void updateProject(ProjectModel projectModel) {
        appRepo.updateProject(projectModel);
    }

    public void deleteProject(ProjectModel projectModel) {
        appRepo.deleteProject(projectModel);
    }

    public LiveData<List<ProjectModel>> getAllProjects() throws ExecutionException, InterruptedException {
        return appRepo.getAllProjects();
    }



}
