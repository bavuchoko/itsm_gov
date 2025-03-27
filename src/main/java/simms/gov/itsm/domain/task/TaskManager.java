package simms.gov.itsm.domain.task;

import simms.gov.itsm.domain.sub.SubTask;
import simms.gov.itsm.domain.task.vo.TaskDateInfo;
import simms.gov.itsm.domain.task.vo.TaskUserInfo;

import java.util.List;


public class TaskManager {


    protected TaskUserInfo userInfo;
    protected TaskDateInfo dateInfo;
    protected boolean isMovable;
    protected boolean isTerminated;
    protected String type;
    protected String title;
    protected String description;

    protected List<SubTask> procedures;

    public TaskManager(List<SubTask> procedure){
        this.procedures = procedure;
    }

}
