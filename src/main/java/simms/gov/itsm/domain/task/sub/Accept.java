package simms.gov.itsm.domain.task.sub;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import simms.gov.itsm.domain.user.entity.Account;


@Entity
public class Accept extends SubTaskManager implements SubTask<Account>{


    @ManyToOne
    @JoinColumn(name = "approval_user")
    private Account approvalUser;



    @Override
    public void doJob(Account item) {
        approvalUser = item;
    }

    @Override
    public boolean isComplete() {
        return done;
    }

}
