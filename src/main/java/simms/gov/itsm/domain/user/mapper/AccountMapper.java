package simms.gov.itsm.domain.user.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import simms.gov.itsm.domain.user.dto.AccountRequestDto;
import simms.gov.itsm.domain.user.dto.AccountResponseDto;
import simms.gov.itsm.domain.user.entity.Account;

@Mapper(
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AccountMapper {
    AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);

    @Mapping(target = "name", source = "accountInfo.name")
    @Mapping(target = "userName", source = "accountInfo.userName")
    @Mapping(target = "email", source = "accountInfo.email")
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "roles", source = "roles")
    AccountResponseDto entityToResponse(Account account);


    @Named("toUserResponseNoRole")
    @Mapping(target = "name", source = "accountInfo.name")
    @Mapping(target = "userName", source = "accountInfo.userName")
    @Mapping(target = "email", source = "accountInfo.email")
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "roles", ignore  = true)
    AccountResponseDto toUserResponseNoRole(Account account);

    @Mapping(target = "accountInfo", expression = "java(new simms.gov.itsm.domain.user.vo.AccountInfo(account.getName(), account.getUserName(), account.getEmail()))")
    @Mapping(target = "password", source = "password")
    @Mapping(target = "roles", source = "roles")
    Account requestToEntity(AccountRequestDto account);


}
