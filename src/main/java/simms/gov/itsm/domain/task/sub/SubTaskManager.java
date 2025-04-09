package simms.gov.itsm.domain.task.sub;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import simms.gov.itsm.domain.task.TaskManager;

import java.time.LocalDateTime;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "sub_type")
@Table(
        name = "sub_task_manager",
        indexes = @Index(name = "idx_subtask_task", columnList = "task_id")
)
@AllArgsConstructor
@NoArgsConstructor
public class SubTaskManager {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    protected TaskManager task;
    protected boolean done;
}
