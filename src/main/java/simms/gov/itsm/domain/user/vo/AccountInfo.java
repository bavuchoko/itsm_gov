package simms.gov.itsm.domain.user.vo;


import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"name", "userName", "email"})
public class AccountInfo{
    private String name;
    private String userName;
    private String email;
}
