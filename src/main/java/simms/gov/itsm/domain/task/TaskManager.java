package simms.gov.itsm.domain.task;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.apache.ibatis.annotations.One;
import simms.gov.itsm.domain.task.sub.SubTask;
import simms.gov.itsm.domain.task.sub.SubTaskManager;
import simms.gov.itsm.domain.task.vo.TaskDateInfo;
import simms.gov.itsm.domain.task.vo.TaskUserInfo;

import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn
@AllArgsConstructor
@NoArgsConstructor
public class TaskManager {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    protected TaskUserInfo userInfo;
    @Embedded
    protected TaskDateInfo dateInfo;
    protected boolean isMovable;
    protected boolean isTerminated;
    protected String type;
    protected String title;
    protected String description;


    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL, orphanRemoval = true)
    protected List<SubTaskManager> procedures;

    public TaskManager(List<SubTaskManager> procedure){
        this.procedures = procedure;
    }

}
