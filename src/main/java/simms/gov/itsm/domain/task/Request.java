package simms.gov.itsm.domain.task;


import jakarta.persistence.Entity;
import lombok.*;
import simms.gov.itsm.domain.task.sub.Accept;

import java.util.Arrays;

@Entity
@Getter
@Builder
public class Request extends TaskManager implements Task {



    public Request(){
        super(Arrays.asList(new Accept()));
    }

    @Override
    public void accept() {

    }

    @Override
    public void bringTo(Task task) {

    }

    @Override
    public void execute() {

    }
}
