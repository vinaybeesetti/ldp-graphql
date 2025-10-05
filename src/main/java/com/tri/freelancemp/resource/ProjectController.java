package com.tri.freelancemp.resource;

import com.tri.freelancemp.entity.Project;
import com.tri.freelancemp.pojo.ProjectSearchInput;
import com.tri.freelancemp.service.ProjectService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @QueryMapping
    public Project getProjectById(@Argument Integer id) {
        return this.projectService.getProjectById(id);
    }

    @QueryMapping
    public List<Project> getAllProjects(@Argument ProjectSearchInput searchFilter, @Argument Integer page, @Argument Integer size) {
        return this.projectService.getAllProjects(searchFilter, page, size);
    }

    @MutationMapping
    public Project createNewProject(@Argument Project project) {
        return this.projectService.addProject(project);
    }
}
