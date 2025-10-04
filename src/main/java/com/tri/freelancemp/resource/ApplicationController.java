package com.tri.freelancemp.resource;

import com.tri.freelancemp.entity.Application;
import com.tri.freelancemp.pojo.AppApprovalInput;
import com.tri.freelancemp.pojo.ApplicationInput;
import com.tri.freelancemp.service.ApplicationService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

@Controller
public class ApplicationController {

    private final ApplicationService applicationService;

    public ApplicationController(ApplicationService applicationService){
        this.applicationService = applicationService;
    }

    @MutationMapping
    public Application applyForProject(@Argument ApplicationInput applicationInput){
        return applicationService.applyForProject(applicationInput);
    }

    @MutationMapping
    public Application applicationApproval(@Argument AppApprovalInput appApprovalInput){
        return applicationService.applicationApproval(appApprovalInput);
    }
}
