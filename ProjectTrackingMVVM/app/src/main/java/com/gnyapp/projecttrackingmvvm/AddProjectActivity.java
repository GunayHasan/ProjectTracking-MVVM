package com.gnyapp.projecttrackingmvvm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.gnyapp.projecttrackingmvvm.ViewModel.ProjectViewModel;
import com.gnyapp.projecttrackingmvvm.databinding.ActivityAddProjectBinding;

public class AddProjectActivity extends AppCompatActivity {

    private ActivityAddProjectBinding binding;
    private String title, language;
    private int watchers, issues;
    private String[] langs = {"Java", "Kotlin", "Python", "C#", "C++"};
    private ProjectViewModel projectViewModel;
    private ProjectModel projectModel;
    private boolean isEdit = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddProjectBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        projectViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(ProjectViewModel.class);

        if(getIntent().hasExtra("model")) {
            projectModel = getIntent().getParcelableExtra("model");
            binding.editIssues.setText(String.valueOf(projectModel.issues));
            binding.editWatchers.setText(String.valueOf(projectModel.watcher));
            binding.editTitle.setText(projectModel.title);
            binding.editLanguages.setText(projectModel.language);
            isEdit = true;
        }


        binding.addButton.setOnClickListener(view -> {

            if(isEdit) {
                title = binding.editTitle.getText().toString().trim();
                language = binding.editLanguages.getText().toString().trim();
                watchers = Integer.parseInt(binding.editWatchers.getText().toString().trim());
                issues = Integer.parseInt(binding.editIssues.getText().toString().trim());

                projectModel.issues = issues;
                projectModel.title = title;
                projectModel.language = language;
                projectModel.watcher = watchers;

                projectViewModel.updateProject(projectModel);

                Toast.makeText(this, "Updated", Toast.LENGTH_SHORT).show();
            }else {
                title = binding.editTitle.getText().toString().trim();
                language = binding.editLanguages.getText().toString().trim();
                watchers = Integer.parseInt(binding.editWatchers.getText().toString().trim());
                issues = Integer.parseInt(binding.editIssues.getText().toString().trim());


                projectModel = new ProjectModel();

                projectModel.issues = issues;
                projectModel.title = title;
                projectModel.language = language;
                projectModel.watcher = watchers;

                projectViewModel.insertProject(projectModel);
                Toast.makeText(this, "Inserted", Toast.LENGTH_SHORT).show();

                finish();
            }



        });


    }


}