package simms.gov.itsm.domain.task;

import org.springframework.stereotype.Component;
import simms.gov.itsm.domain.task.dto.TaskRequestDto;
import simms.gov.itsm.domain.task.mapper.TaskMapper;
import simms.gov.itsm.domain.task.sub.Accept;

import java.util.List;

@Component
public class TaskFactory {

    public Claim claimOf(TaskRequestDto taskManager) {


        if(taskManager.getCreateDate()==null) throw new IllegalArgumentException("TaskRequestDto must have createDate");
        if(taskManager.getCreateUser()==null) throw new IllegalArgumentException("TaskRequestDto must have createUser");

        Claim claim = TaskMapper.INSTANCE.toClaim(taskManager);

        Accept accept = new Accept();
        accept.setTask(claim);

        claim.setProcedures(List.of(accept));

        return claim;
    }

}
