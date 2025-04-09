package simms.gov.itsm.domain.task;


import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import simms.gov.itsm.domain.task.sub.Accept;
import simms.gov.itsm.domain.task.sub.SubTaskManager;
import simms.gov.itsm.domain.task.vo.ClaimDate;
import simms.gov.itsm.domain.user.entity.Account;

import java.util.Arrays;
import java.util.List;

@Entity
@Getter
@SuperBuilder
@DiscriminatorValue("Request")
@NoArgsConstructor
public class Claim extends TaskManager implements Task {

    @Embedded
    private ClaimDate claimDate;

    private String etc;

    @Override
    public void bringTo(Task task) {

    }

    @Override
    public void execute() {

    }

    public void setProcedures(List<SubTaskManager> procedures) {
        this.procedures = procedures;
    }

    //접수처리
    public void accept(Account user) {
        procedures.stream()
                .filter(p -> p instanceof Accept)
                .map(p -> (Accept)p)
                .filter(p -> !p.isComplete())
                .findFirst()
                .ifPresentOrElse(
                        p -> p.done(user),
                        ()->{
                            throw new IllegalArgumentException("No Accept claim found");
                        }
                );
    }
}
