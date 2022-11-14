package com.amaris.task.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

	@Query("SELECT t FROM Task as t WHERE t.assignee.id IS NULL")
	List<Task> getAllUnassigned();
	
	@Query("SELECT t FROM Task as t WHERE t.assignee.id IS NOT NULL")
	List<Task> getAllAssigned();
	
	@Query("SELECT t FROM Task as t WHERE t.assignee.id =?1")
	List<Task> getAllByAssegnee(long id);
	
	@Modifying
	@Query("update Task t set t.assignee = NULL WHERE t.id = ?1")
	void removeAssignee(Long id);

	
	@Modifying
	@Query("update Task t set t.description = ?2, t.dueDate = ?3, t.lastUpdate = ?4, t.assignee = ?5 WHERE t.id = ?1")
	void updateTask(Long id, String description, LocalDate dueDate, LocalDateTime lastUpdate, Employee assigneeId);
}
