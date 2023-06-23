package com.juliano.task.form;

import com.juliano.task.enumeration.TaskStatus;
import com.juliano.task.model.Task;
import com.juliano.task.repository.TaskRepository;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public class UpdateStatusTaskForm {

	@NotNull
	@Length(min = 0)
	private String status;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Task converter(Long id, TaskRepository taskRepository) {
		Task task = taskRepository.findById(id).get();
		task.setStatus(TaskStatus.valueOf(status));
		return task;
	}

	public Task converter() {
		return new Task(status);
	}

}
