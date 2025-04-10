package simms.gov.itsm.domain.task.sub;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import simms.gov.itsm.domain.task.TaskManager;
import simms.gov.itsm.domain.user.entity.Account;


@Getter
@Entity
public class Accept extends SubTaskManager implements SubTask{


    @ManyToOne
    @JoinColumn(name = "approval_user")
    private Account approvalUser;

    public void setTask(TaskManager task) {
        super.task = task;
    }

    @Override
    public void done(Contentable item) {
        if (item instanceof Account user) {
            this.approvalUser = user;
            this.done = true;
        } else {
            throw new IllegalArgumentException("Invalid content type for Review task");
        }
    }

    @Override
    public boolean isComplete() {
        return done;
    }

}
