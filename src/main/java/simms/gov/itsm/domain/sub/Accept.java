package simms.gov.itsm.domain.sub;

import simms.gov.itsm.domain.user.Account;

public class Accept extends SubTaskManager implements SubTask{

    private Account approvalUser;



    @Override
    public void doJob(Contentable item) {
        approvalUser = (Account) item;
    }

    @Override
    public boolean isComplete() {
        return done;
    }

}
