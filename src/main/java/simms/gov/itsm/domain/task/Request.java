package simms.gov.itsm.domain.task;


import lombok.*;
import simms.gov.itsm.domain.sub.Accept;

import java.util.Arrays;

@Builder
@Getter
@Setter
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
