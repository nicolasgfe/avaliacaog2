package com.juliano.task.form;

import com.juliano.task.enumeration.TaskStatus;
import com.juliano.task.model.Task;
import com.juliano.task.repository.TaskRepository;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public class TaskForm {
	@NotNull
	@Length(min = 0, max = 60)
	private String title;

	@NotNull
	@Length(min = 0,max = 300)
	private String description;

	@NotNull
	@Length(min = 0)
	private String status;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public Task converter(Long id, TaskRepository taskRepository) {
		Task task = taskRepository.findById(id).get();
		task.setTitle(title);
		task.setDescription(description);
		task.setStatus(TaskStatus.PENDING);
		return task;
	}
	
	public Task converter() {
		return new Task(title, description, status);
	}

}
