package com.tri.freelancemp.pojo;

import com.tri.freelancemp.entity.Status;
import lombok.Data;

@Data
public class AppApprovalInput {

    private Integer applicationId;
    private Integer clientId;
    private Status status;
}
