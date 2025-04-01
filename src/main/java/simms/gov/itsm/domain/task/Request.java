package simms.gov.itsm.domain.task;


import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import simms.gov.itsm.domain.task.sub.Accept;
import simms.gov.itsm.domain.task.vo.ReqeustDate;

import java.util.Arrays;

@Entity
@Getter
@SuperBuilder
@DiscriminatorValue("Request")
public class Request extends TaskManager implements Task {

    @Embedded
    private ReqeustDate requestDateInfo;

    private String etc;

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
