package simms.gov.itsm.domain.task.sub;

public interface SubTask {

    void done(Contentable t);
    boolean isComplete();
}
