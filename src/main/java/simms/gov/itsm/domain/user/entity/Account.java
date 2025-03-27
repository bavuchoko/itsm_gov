package simms.gov.itsm.domain.user.entity;


import jakarta.persistence.*;
import lombok.*;
import simms.gov.itsm.domain.task.sub.Contentable;
import simms.gov.itsm.domain.user.vo.AccountInfo;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="id", callSuper = false)
public class Account implements Contentable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private AccountInfo accountInfo;

    private String password;

    @ElementCollection(fetch = FetchType.LAZY)
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private Set<AccountRole> roles = new HashSet<>();

}
