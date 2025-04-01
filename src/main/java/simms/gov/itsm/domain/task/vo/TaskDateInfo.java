package simms.gov.itsm.domain.task.vo;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@EqualsAndHashCode
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskDateInfo {
    private LocalDateTime createDate;
    private LocalDateTime updateDate;

}
