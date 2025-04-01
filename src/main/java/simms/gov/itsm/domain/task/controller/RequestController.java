package simms.gov.itsm.domain.task.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import simms.gov.itsm.common.CurrentUser;
import simms.gov.itsm.domain.task.dto.TaskRequestDto;
import simms.gov.itsm.domain.task.service.RequestService;
import simms.gov.itsm.domain.user.entity.Account;

@RestController
@RequestMapping(value = "/v1/request",  produces = "application/json;charset=UTF-8")
@RequiredArgsConstructor
@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
public class RequestController {

    private final RequestService requestService;

    @GetMapping
    public String request() {
        return "Hello World";
    }

    @PostMapping
    public String postRequestTask(
            @CurrentUser Account user,
            @RequestBody TaskRequestDto body
    ) {

        requestService.saveRequestTask(user, body);
        return "Hello World";
    }
}
