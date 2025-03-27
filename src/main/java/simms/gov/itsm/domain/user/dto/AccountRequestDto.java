package simms.gov.itsm.domain.user.dto;


import jakarta.validation.constraints.Email;
import lombok.*;
import simms.gov.itsm.domain.user.entity.AccountRole;

import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountRequestDto {

    private String name;
    private String userName;
    @Email(message = "Invalid email format")
    private String email;

    private String password;

    private Set<AccountRole> roles;
}
