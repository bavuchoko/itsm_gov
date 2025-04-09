package simms.gov.itsm.domain.task;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import simms.gov.itsm.domain.task.sub.SubTaskManager;
import simms.gov.itsm.domain.task.vo.TaskDateInfo;
import simms.gov.itsm.domain.task.vo.TaskUserInfo;

import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "task_type")
@Table(name = "task_manager", indexes = @Index(name = "idx_task_type", columnList = "task_type"))
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Getter
public class TaskManager {

    @OneToOne(fetch = FetchType.EAGER)
    private TaskManager Before;

    @ManyToOne(fetch = FetchType.EAGER)
    private TaskManager After;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Embedded
    protected TaskUserInfo userInfo;
    @Embedded
    protected TaskDateInfo dateInfo;
    protected boolean isMovable;
    protected boolean isTerminated;
    protected String title;
    protected String description;


    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL, orphanRemoval = true)
    protected List<SubTaskManager> procedures;


}
