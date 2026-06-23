package com.nexushr.nexushr.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.nexushr.nexushr.entity.Attendance;
import com.nexushr.nexushr.service.AttendanceService;

@RestController
@RequestMapping("/attendance")
@CrossOrigin("*")
public class AttendanceController {

    @Autowired
    private AttendanceService service;

    // CHECK-IN
    @PostMapping("/checkin/{employeeId}")
    public Attendance checkIn(
            @PathVariable Long employeeId) {

        return service.checkIn(employeeId);
    }

    // CHECK-OUT
    @PostMapping("/checkout/{id}")
    public Attendance checkOut(
            @PathVariable Long id) {

        return service.checkOut(id);
    }
    @GetMapping("/calendar/{employeeId}")
    public List<Attendance> getCalendar(
            @PathVariable Long employeeId) {

        return service.getEmployeeAttendance(
                employeeId
        );
    }
    // GET ALL ATTENDANCE
    @GetMapping
    public List<Attendance> getAllAttendance() {

        return service.getAllAttendance();
    }

    // GET EMPLOYEE ATTENDANCE
    @GetMapping("/{employeeId}")
    public List<Attendance> getAttendanceByEmployeeId(
            @PathVariable Long employeeId) {

        return service.getAttendanceByEmployeeId(employeeId);
    }
}