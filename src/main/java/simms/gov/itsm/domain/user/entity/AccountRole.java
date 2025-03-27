package simms.gov.itsm.domain.user.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AccountRole {
    VIEW(5),
    USER(10),
    ADMIN(15),
    SYSTEM(20)
    ;

    public final int numericValue;


}