package simms.gov.itsm.domain.task.dto;

import jakarta.persistence.ManyToOne;
import lombok.*;
import simms.gov.itsm.domain.task.sub.SubTaskManager;
import simms.gov.itsm.domain.user.dto.AccountResponseDto;
import simms.gov.itsm.domain.user.entity.Account;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class ClaimResponse {


    private AccountResponseDto createUser;
    private AccountResponseDto updateUser;
    private AccountResponseDto operateUser;
    private AccountResponseDto reviewUser;

    private LocalDateTime createDate;
    private LocalDateTime updateDate;

    protected boolean isMovable;
    protected boolean isTerminated;
    protected String title;
    protected String description;

    private LocalDateTime executeDate;
    private LocalDateTime requestStartDate;
    private LocalDateTime requestEndDate;
    private String etc;
    private List<SubTaskManager> procedures;
}
