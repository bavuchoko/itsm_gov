package simms.gov.itsm.domain.task.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import simms.gov.itsm.domain.task.Claim;
import simms.gov.itsm.domain.task.dto.ClaimResponse;
import simms.gov.itsm.domain.task.dto.TaskRequestDto;
import simms.gov.itsm.domain.task.vo.ClaimDate;
import simms.gov.itsm.domain.task.vo.TaskDateInfo;
import simms.gov.itsm.domain.task.vo.TaskUserInfo;
import simms.gov.itsm.domain.user.mapper.AccountMapper;

@Mapper(
        unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {AccountMapper.class})
public interface TaskMapper {

    TaskMapper INSTANCE = Mappers.getMapper(TaskMapper.class);

    @Mapping(target = "userInfo", expression = "java(toTaskUserInfo(dto))")
    @Mapping(target = "dateInfo", expression = "java(toTaskDateInfo(dto))")
    @Mapping(target = "claimDate", expression = "java(toClaimDateInfo(dto))")
    Claim toClaim(TaskRequestDto dto);

    default TaskUserInfo toTaskUserInfo(TaskRequestDto dto) {
        return TaskUserInfo.builder()
                .createUser(dto.getCreateUser())
                .updateUser(dto.getUpdateUser())
                .operateUser(dto.getOperateUser())
                .reviewUser(dto.getReviewUser())
                .build();
    }

    default TaskDateInfo toTaskDateInfo(TaskRequestDto dto) {
        return TaskDateInfo.builder()
                .createDate(dto.getCreateDate())
                .updateDate(dto.getUpdateDate())
                .build();
    }

    default ClaimDate toClaimDateInfo(TaskRequestDto dto) {
        return ClaimDate.builder()
                .executeDate(dto.getExecuteDate())
                .requestStartDate(dto.getRequestStartDate())
                .requestEndDate(dto.getRequestEndDate())
                .build();
    }



    @Mapping(source = "userInfo.createUser", target = "createUser", qualifiedByName ="toUserResponseNoRole" )
    @Mapping(source = "userInfo.updateUser", target = "updateUser", qualifiedByName ="toUserResponseNoRole" )
    @Mapping(source = "userInfo.operateUser", target = "operateUser", qualifiedByName ="toUserResponseNoRole" )
    @Mapping(source = "userInfo.reviewUser", target = "reviewUser", qualifiedByName ="toUserResponseNoRole" )

    @Mapping(source = "dateInfo.createDate", target = "createDate")
    @Mapping(source = "dateInfo.updateDate", target = "updateDate")

    @Mapping(source = "claimDate.executeDate", target = "executeDate")
    @Mapping(source = "claimDate.requestStartDate", target = "requestStartDate")
    @Mapping(source = "claimDate.requestEndDate", target = "requestEndDate")
    @Mapping(source = "procedures", target = "procedures")
    ClaimResponse toClaimResponse(Claim entity);

}
