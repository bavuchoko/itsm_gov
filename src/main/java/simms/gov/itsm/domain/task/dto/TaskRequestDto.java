package simms.gov.itsm.domain.task.dto;

import lombok.*;
import simms.gov.itsm.domain.task.sub.SubTaskManager;
import simms.gov.itsm.domain.user.entity.Account;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TaskRequestDto {
    private Account createUser;                 //                   -필수 요청, 장애,
    private Account updateUser;                 //
    private Account operateUser;                //
    private Account reviewUser;                 //
    private LocalDateTime createDate;           // 등록일             -필수 요청, 장애
    private LocalDateTime updateDate;           // 수정일
    private LocalDateTime executeDate;          // 처리일
    private LocalDateTime requestStartDate;     // 요청 시작일         -필수 요청
    private LocalDateTime requestEndDate;       // 요청 마감일         -필수 요청
    private String title;                       // 제목               -
    private String description;                 // 설명               -

}
