package com.gnyapp.projecttrackingmvvm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.AdapterView;

import com.gnyapp.projecttrackingmvvm.Adapter.ProjectAdapter;
import com.gnyapp.projecttrackingmvvm.ViewModel.ProjectViewModel;
import com.gnyapp.projecttrackingmvvm.databinding.ActivityMainBinding;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity implements OnClickItemInterface {

    private ActivityMainBinding binding;
    private ProjectViewModel viewModel;
    private ProjectAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.recylerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ProjectAdapter(this);
        binding.recylerView.setAdapter(adapter);

        binding.addProjectButton.setOnClickListener(view -> {
            startActivity(new Intent(MainActivity.this, AddProjectActivity.class));
        });

        viewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(ProjectViewModel.class);
        try {
            //adapter.setProjects(viewModel.getAllProjects());
            viewModel.getAllProjects().observe(MainActivity.this, new Observer<List<ProjectModel>>() {
                @Override
                public void onChanged(List<ProjectModel> projectModels) {
                    if(projectModels != null) {
                        adapter.setProjects(projectModels);
                    }
                }
            });
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onclickItem(ProjectModel projectModel, boolean isEdit) {
        if(isEdit) {
            Intent intent = new Intent(MainActivity.this, AddProjectActivity.class);
            intent.putExtra("model", projectModel);
            startActivity(intent);
        }else{
            viewModel.deleteProject(projectModel);
        }
    }
}