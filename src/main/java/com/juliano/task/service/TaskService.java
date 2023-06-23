package com.juliano.task.service;

import java.util.List;
import java.util.Optional;

import com.juliano.task.enumeration.TaskStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.juliano.task.dto.TaskDTO;
import com.juliano.task.form.TaskForm;
import com.juliano.task.form.UpdateStatusTaskForm;
import com.juliano.task.form.UpdateTaskForm;
import com.juliano.task.model.Task;
import com.juliano.task.repository.TaskRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class TaskService {
	private TaskRepository taskRepository;

	public TaskService() {
	}

	public Task createTask(TaskForm task) {
		Task newTask = task.converter();
		return taskRepository.save(newTask);
	}

	public Task updateTask(Long id, UpdateTaskForm updateTaskForm) {
		Optional<Task> task = taskRepository.findById(id);
		return updateTaskForm.converter(id, (TaskRepository) updateTaskForm);
	}
	
	public Task updateTask(Long id, UpdateStatusTaskForm updateStatusTaskForm) {
		Task newTask = updateStatusTaskForm.converter(id, (TaskRepository) updateStatusTaskForm);
		return newTask;
	}

	public Page<TaskDTO> getAllTasks(Pageable pagination) {
		Page<Task> task = taskRepository.findAll(pagination);
		return TaskDTO.converter(task);
	}
	
	public Optional<Task> findById(Long id) {
		return taskRepository.findById(id);
	}

	public void deleteTask(Long id) {
		taskRepository.deleteById(id);
	}
}
