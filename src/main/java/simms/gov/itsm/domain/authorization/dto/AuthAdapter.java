package simms.gov.itsm.domain.authorization.dto;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import simms.gov.itsm.domain.authorization.entity.AuthJpaEntity;
import simms.gov.itsm.domain.user.entity.AccountJapEntity;
import simms.gov.itsm.domain.user.entity.AccountRole;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

public class AuthAdapter extends User {

    private AccountJapEntity account;;

    public AuthAdapter(AuthJpaEntity account) {
        super(account.getUserName(), account.getPassword(), authorities(account.getRoles()));
    }

    private static Collection<? extends GrantedAuthority> authorities(Set<AccountRole> roles) {
        return roles.stream().map(r -> new SimpleGrantedAuthority("ROLE_" + r.name()))
                .collect(Collectors.toSet());
    }
    public AccountJapEntity getAccount() {return account;}
}
