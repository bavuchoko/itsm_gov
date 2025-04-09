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
public class ClaimDate {

    private LocalDateTime executeDate;
    private LocalDateTime requestStartDate;
    private LocalDateTime requestEndDate;
}
