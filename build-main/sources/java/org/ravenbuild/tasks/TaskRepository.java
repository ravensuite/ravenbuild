package org.ravenbuild.tasks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TaskRepository {
	private final Map<String, TaskInfo> tasks = new HashMap<>();
	
	private final List<TaskGroup> allTaskGroups = new ArrayList<>();
	private final Map<String, TaskGroup> taskGroupsByName = new HashMap<>();
	
	public Task findTask(final String taskName) {
		if (tasks.containsKey(taskName)) {
			return tasks.get(taskName).task;
		}
	
		return null;
	}
	
	public Class getTaskOptionsType(final String taskName) {
		if (tasks.containsKey(taskName)) {
			return tasks.get(taskName).taskOptionsType;
		}
		return null;
	}
	
	public void add(final String taskName, final TaskInfo taskInfo, final String taskGroupName) {
		tasks.put(taskName, taskInfo);
		
		final TaskGroup group = findOrAddTaskGroup(taskGroupName);
		group.addTask(taskName, taskInfo.getTask());
	}
	
	public List<TaskGroup> allTaskGroups() {
		return allTaskGroups;
	}
	
	public static class TaskInfo {
		private final Task task;
		private final Class<?> taskOptionsType;
		
		public TaskInfo(final Task task, final Class<?> taskOptionsType) {
			this.task = task;
			this.taskOptionsType = taskOptionsType;
		}
		
		public Task getTask() {
			return task;
		}
		
		public Class<?> getTaskOptionsType() {
			return taskOptionsType;
		}
	}
	
	private TaskGroup findOrAddTaskGroup(final String taskGroupName) {
		addTaskGroupIfMissing(taskGroupName);
		return taskGroupsByName.get(taskGroupName);
	}
	
	private void addTaskGroupIfMissing(final String taskGroupName) {
		if(!taskGroupsByName.containsKey(taskGroupName)) {
			final TaskGroup group = new TaskGroup(taskGroupName);
			taskGroupsByName.put(taskGroupName, group);
			allTaskGroups.add(group);
		}
	}
	
}