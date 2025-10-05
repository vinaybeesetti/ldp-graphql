package com.tri.freelancemp.service;

import com.tri.freelancemp.entity.Project;
import com.tri.freelancemp.entity.Role;
import com.tri.freelancemp.entity.User;
import com.tri.freelancemp.exceptions.ObjectNotFoundException;
import com.tri.freelancemp.exceptions.UnauthorizedClientException;
import com.tri.freelancemp.pojo.ProjectSearchInput;
import com.tri.freelancemp.repository.ProjectRepository;
import com.tri.freelancemp.repository.UserRepository;
import com.tri.freelancemp.util.ProjectSpecification;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;

    public ProjectService(ProjectRepository projectRepository,  UserRepository userRepository) {
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
    }

    public Project getProjectById(Integer id) {
        return this.projectRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Project not found"));
    }

    public List<Project> getAllProjects(ProjectSearchInput searchInput, Integer page, Integer size) {
        // Provide default values for pagination if not supplied, which is a good practice for enterprise applications.
        int pageNumber = (page != null && page >= 0) ? page : 0;
        int pageSize = (size != null && size > 0) ? size : 10; // Default size to 10
        Pageable pageable = PageRequest.of(pageNumber, pageSize);

        if (searchInput == null) {
            return this.projectRepository.findAll(pageable).getContent();
        }

        Specification<Project> spec = Specification.allOf(ProjectSpecification.budgetGreaterThanEqual(searchInput.getMinBudget()),
                ProjectSpecification.budgetLessThanEqual(searchInput.getMaxBudget()),
                ProjectSpecification.keywordSearch(searchInput.getSearch()));

        return this.projectRepository.findAll(spec, pageable).getContent();
    }

    public Project addProject(Project project) {

        Integer clientId = project.getClient_id();
        User user = this.userRepository.findById(clientId).orElseThrow(() -> new ObjectNotFoundException("Client id not found"));
        Role role = user.getRole();
        if (role != Role.CLIENT) {
            throw new UnauthorizedClientException("Only client can add projects");
        }
        project.setClient(user);
        return this.projectRepository.save(project);
    }
}
