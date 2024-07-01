package com.gnyapp.projecttrackingmvvm.Adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.gnyapp.projecttrackingmvvm.OnClickItemInterface;
import com.gnyapp.projecttrackingmvvm.ProjectModel;
import com.gnyapp.projecttrackingmvvm.R;
import com.gnyapp.projecttrackingmvvm.databinding.ProjectItemLayoutBinding;

import java.util.ArrayList;
import java.util.List;

public class ProjectAdapter extends RecyclerView.Adapter<ProjectAdapter.ViewHolder> {

    List<ProjectModel> projectModelList;
    private OnClickItemInterface onClickItemInterface;

    public ProjectAdapter(OnClickItemInterface onClickItemInterface) {
        this.onClickItemInterface = onClickItemInterface;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ProjectItemLayoutBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.project_item_layout, parent, false);

        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if(projectModelList != null) {
            ProjectModel projectModel = projectModelList.get(position);
            holder.binding.setProjectModel(projectModel);
            holder.binding.setListener(onClickItemInterface);
        }
    }

    @Override
    public int getItemCount() {
        if(projectModelList != null) {
            return projectModelList.size();
        }else{
            return 0;
        }

    }

    public void setProjects(List<ProjectModel> list) {
        projectModelList = list;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ProjectItemLayoutBinding binding;
        public ViewHolder(ProjectItemLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

}