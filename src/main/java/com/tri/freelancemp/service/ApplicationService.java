package com.tri.freelancemp.service;

import com.tri.freelancemp.entity.*;
import com.tri.freelancemp.exceptions.ObjectNotFoundException;
import com.tri.freelancemp.exceptions.UnauthorizedClientException;
import com.tri.freelancemp.pojo.AppApprovalInput;
import com.tri.freelancemp.pojo.ApplicationInput;
import com.tri.freelancemp.repository.ApplicationRepository;
import com.tri.freelancemp.repository.ProjectRepository;
import com.tri.freelancemp.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;

    public ApplicationService(ApplicationRepository applicationRepository,
                              UserRepository userRepository,
                              ProjectRepository projectRepository) {
        this.applicationRepository = applicationRepository;
        this.userRepository = userRepository;
        this.projectRepository = projectRepository;
    }

    public Application applyForProject(ApplicationInput applicationInput){

        Application application = new Application();
        application.setCoverLetter(applicationInput.getCoverLetter());
        application.setStatus(Status.PENDING);

        Integer applicantId = applicationInput.getApplicant_id();
        User user = this.userRepository.findById(applicantId).orElseThrow(() -> new ObjectNotFoundException("Applicant ID not found"));

        if (user.getRole() != Role.FREELANCER) {
            throw new UnauthorizedClientException("Only Freelancers can apply for this application");
        }
        application.setApplicant(user);

        Integer projectId = applicationInput.getProject_id();
        Project project = projectRepository.findById(projectId).orElseThrow(() -> new ObjectNotFoundException("Project ID not found"));
        application.setProject(project);

        return applicationRepository.save(application);
    }

    public Application applicationApproval(AppApprovalInput appApprovalInput){
        Application application = applicationRepository.findById(appApprovalInput.getApplicationId())
                .orElseThrow(() -> new ObjectNotFoundException("Application ID not found"));

        User user = userRepository.findById(appApprovalInput.getClientId()).orElseThrow(() -> new ObjectNotFoundException("Client ID not found"));

        if (user.getRole() != Role.CLIENT) {
            throw new UnauthorizedClientException("Only clients can approve/reject an application");
        }

        application.setStatus(appApprovalInput.getStatus());

        return applicationRepository.save(application);
    }
}
