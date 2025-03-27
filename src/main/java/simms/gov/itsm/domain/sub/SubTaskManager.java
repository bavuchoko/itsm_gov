package simms.gov.itsm.domain.sub;

import simms.gov.itsm.domain.user.Account;
import simms.gov.itsm.domain.user.entity.AccountJapEntity;

import java.time.LocalDateTime;

public abstract class SubTaskManager {

    protected boolean done;
    protected String content;
    protected AccountJapEntity operateUser;
    protected LocalDateTime operateTime;

}
