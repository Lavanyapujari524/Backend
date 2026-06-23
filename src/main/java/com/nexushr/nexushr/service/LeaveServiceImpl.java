package com.nexushr.nexushr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.nexushr.nexushr.entity.LeaveRequest;
import com.nexushr.nexushr.repository.LeaveRepository;

@Service
public class LeaveServiceImpl
        implements LeaveService {

    @Autowired
    private LeaveRepository repository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Override
    public LeaveRequest applyLeave(
            LeaveRequest leave) {

        leave.setStatus("PENDING");

        LeaveRequest savedLeave =
                repository.save(leave);

        // Real-time notification
        messagingTemplate.convertAndSend(
                "/topic/notifications",
                "New Leave Request Submitted"
        );

        return savedLeave;
    }

    @Override
    public List<LeaveRequest> getAllLeaves() {

        return repository.findAll();
    }

    @Override
    public List<LeaveRequest> getEmployeeLeaves(
            Long employeeId) {

        return repository.findByEmployeeId(
                employeeId
        );
    }

    @Override
    public LeaveRequest approveLeave(
            Long id) {

        LeaveRequest leave =
                repository.findById(id)
                        .orElse(null);

        if (leave != null) {

            leave.setStatus("APPROVED");

            LeaveRequest updatedLeave =
                    repository.save(leave);

            // Email Notification
            emailService.sendEmail(
                    "employee@gmail.com",
                    "Leave Approved",
                    "Your leave request has been approved."
            );

            // Real-time Notification
            messagingTemplate.convertAndSend(
                    "/topic/notifications",
                    "Leave Approved"
            );

            return updatedLeave;
        }

        return null;
    }

    @Override
    public LeaveRequest rejectLeave(
            Long id) {

        LeaveRequest leave =
                repository.findById(id)
                        .orElse(null);

        if (leave != null) {

            leave.setStatus("REJECTED");

            LeaveRequest updatedLeave =
                    repository.save(leave);

            // Email Notification
            emailService.sendEmail(
                    "employee@gmail.com",
                    "Leave Rejected",
                    "Your leave request has been rejected."
            );

            // Real-time Notification
            messagingTemplate.convertAndSend(
                    "/topic/notifications",
                    "Leave Rejected"
            );

            return updatedLeave;
        }

        return null;
    }
}