package simms.gov.itsm.domain.task.vo;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import simms.gov.itsm.domain.user.entity.AccountJapEntity;

@Getter
@Builder
@EqualsAndHashCode
public class TaskUserInfo {

    private final AccountJapEntity createUser;
    private final AccountJapEntity updateUser;
    private final AccountJapEntity operateUser;
    private final AccountJapEntity reviewUser;

}
