package com.nexushr.nexushr.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nexushr.nexushr.entity.Attendance;
import com.nexushr.nexushr.repository.AttendanceRepository;

@Service
public class AttendanceService {

    @Autowired
    private AttendanceRepository repository;

    // CHECK-IN
    public Attendance checkIn(Long employeeId) {

        Attendance attendance = new Attendance();

        attendance.setEmployeeId(employeeId);

        attendance.setDate(LocalDate.now());

        attendance.setCheckIn(LocalTime.now());

        attendance.setStatus("PRESENT");

        return repository.save(attendance);
    }
    public List<Attendance> getEmployeeAttendance(
            Long employeeId) {

        return repository
                .findByEmployeeId(
                        employeeId
                );
    }
    // CHECK-OUT
    public Attendance checkOut(Long id) {

        Attendance attendance =
                repository.findById(id).orElse(null);

        if(attendance != null) {

            attendance.setCheckOut(LocalTime.now());

            return repository.save(attendance);
        }

        return null;
    }

    // GET ALL
    public List<Attendance> getAllAttendance() {

        return repository.findAll();
    }

    // GET BY EMPLOYEE ID
    public List<Attendance> getAttendanceByEmployeeId(
            Long employeeId) {

        return repository.findByEmployeeId(employeeId);
    }
}