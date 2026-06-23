package com.nexushr.nexushr.service;

import java.util.List;

import com.nexushr.nexushr.entity.LeaveRequest;

public interface LeaveService {

    LeaveRequest applyLeave(
            LeaveRequest leave);

    List<LeaveRequest> getAllLeaves();

    List<LeaveRequest> getEmployeeLeaves(
            Long employeeId);

    LeaveRequest approveLeave(
            Long id);

    LeaveRequest rejectLeave(
            Long id);
}