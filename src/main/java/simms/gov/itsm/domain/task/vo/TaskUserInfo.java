package simms.gov.itsm.domain.task.vo;

import jakarta.persistence.Embeddable;
import jakarta.persistence.ManyToOne;
import lombok.*;
import simms.gov.itsm.domain.user.entity.Account;

@Getter
@EqualsAndHashCode
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskUserInfo {

    @ManyToOne
    private Account createUser;
    @ManyToOne
    private Account updateUser;
    @ManyToOne
    private Account operateUser;
    @ManyToOne
    private Account reviewUser;

}
