package com.gps.action;

import org.json.JSONObject;

import com.gps.orm.Task;
import com.gps.service.TaskService;
import com.gps.util.Util;

public class TaskStateChangeAction extends Action {

	@Override
	public void doAction() throws Exception {
		Task t = getServiceLocator().getTaskService().findById(getInteger("taskId"));
		
		getServiceLocator().getTaskService().changeTaskState(t,this.getShort("taskState"));
		
		JSONObject json = new JSONObject();
		json.put("actualStartTime", Util.FormatDateMid(t.getActualStartTime()));
		json.put("actualFinishTime", Util.FormatDateMid(t.getActualFinishTime()));
		json.put("taskState", TaskService.taskStates.get(t.getTaskState()));
		response.getWriter().write(json.toString());
		request.setAttribute("taskId", String.valueOf(t.getTaskId()));
	}

}
