package com.gps.action;

import com.gps.orm.Task;
import com.gps.service.TaskService;

public class TaskDelAction extends Action {

	@Override
	public void doAction() throws Exception {
		Task t = getServiceLocator().getTaskService().findById(getInteger("recID"));
		t.setTaskState(TaskService.TASK_DEL_STATE);
		getServiceLocator().getTaskService().updateTask(t);
	}

}
