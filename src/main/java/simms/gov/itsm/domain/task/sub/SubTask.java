package simms.gov.itsm.domain.task.sub;

public interface SubTask<T extends Contentable> {

    void doJob(T t);
    boolean isComplete();
}
