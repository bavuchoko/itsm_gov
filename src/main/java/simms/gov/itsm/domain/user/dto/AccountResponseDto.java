package simms.gov.itsm.domain.user.dto;


import lombok.*;
import simms.gov.itsm.domain.user.entity.AccountRole;

import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountResponseDto {

    private String id;
    private String name;
    private String userName;
    private String email;
    private String password;
    private Set<AccountRole> roles;
}
