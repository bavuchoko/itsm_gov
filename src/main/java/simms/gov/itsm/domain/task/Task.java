package simms.gov.itsm.domain.task;

public interface Task {

    void bringTo(Task task);
    void execute();

}
