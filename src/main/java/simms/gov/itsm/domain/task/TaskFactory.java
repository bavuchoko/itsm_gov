package simms.gov.itsm.domain.task;

public class TaskFactory {

    public Request createRequest(TaskManager taskManager) {
        return Request.builder()
                .userInfo(taskManager.userInfo)
                .dateInfo(taskManager.dateInfo)
                .build();
    }
}
