package simms.gov.itsm.domain.authorization.dto;

import jakarta.validation.constraints.Email;
import lombok.*;
import simms.gov.itsm.domain.user.entity.AccountRole;

import java.util.Set;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthRequestDto {

    private String id;
    private String name;
    private String userName;
    @Email(message = "Invalid email format")
    private String email;
    private String password;
    private Set<AccountRole> roles;
}
