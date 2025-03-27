package simms.gov.itsm.domain.task.vo;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.time.LocalDate;

@Builder
@Getter
@EqualsAndHashCode
public class TaskDateInfo {
    private final LocalDate createDate;
    private final LocalDate updateDate;
    private final LocalDate operateDate;
    private final LocalDate reviewDate;
    private final LocalDate executeDate;
    private final LocalDate requestStartDate;
    private final LocalDate requestEndDate;
}
