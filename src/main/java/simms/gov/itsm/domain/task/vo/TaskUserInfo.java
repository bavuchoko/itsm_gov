package simms.gov.itsm.domain.task.vo;

import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import simms.gov.itsm.domain.user.entity.Account;

@Getter
@Builder
@EqualsAndHashCode
public class TaskUserInfo {

    @ManyToOne
    private final Account createUser;
    @ManyToOne
    private final Account updateUser;
    @ManyToOne
    private final Account operateUser;
    @ManyToOne
    private final Account reviewUser;

}
