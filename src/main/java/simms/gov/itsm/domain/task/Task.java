package simms.gov.itsm.domain.task;

public interface Task {

    void accept();
    void bringTo(Task task);
    void execute();

}
