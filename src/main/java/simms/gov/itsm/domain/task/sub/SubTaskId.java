package simms.gov.itsm.domain.task.sub;

import simms.gov.itsm.domain.task.TaskManager;

import java.io.Serializable;

public class SubTaskId implements Serializable {
    private Long id;
    private TaskManager task;
}
