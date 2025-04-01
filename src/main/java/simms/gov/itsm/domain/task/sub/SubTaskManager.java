package simms.gov.itsm.domain.task.sub;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import simms.gov.itsm.domain.task.TaskManager;

import java.time.LocalDateTime;


@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn
@AllArgsConstructor
@NoArgsConstructor
@IdClass(SubTaskId.class)
public class SubTaskManager {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    protected boolean done;
    protected String content;
    protected LocalDateTime createDate;
    protected LocalDateTime executeDate;
    @Id
    @ManyToOne
    @JoinColumn(name = "task")
    private TaskManager task;

}
