package com.example.pet_project.repository;

import com.example.pet_project.entity.Schedule;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;

public interface ScheduleRep extends CrudRepository<Schedule, Integer> {
     Iterable<Schedule> findDistinctByDateTimeOfReceipt(Date dateTimeOfReceipt);
}
