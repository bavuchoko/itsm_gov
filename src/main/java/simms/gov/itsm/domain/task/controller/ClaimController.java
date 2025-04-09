package simms.gov.itsm.domain.task.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import simms.gov.itsm.common.CurrentUser;
import simms.gov.itsm.domain.task.dto.ClaimResponse;
import simms.gov.itsm.domain.task.dto.TaskRequestDto;
import simms.gov.itsm.domain.task.service.ClaimService;
import simms.gov.itsm.domain.user.entity.Account;

@RestController
@RequestMapping(value = "/v1/claim",  produces = "application/json;charset=UTF-8")
@RequiredArgsConstructor
@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
public class ClaimController {

    private final ClaimService ClaimService;

    @GetMapping
    public String request() {
        return "Hello World";
    }

    @PostMapping
    public void createClaim(
            @CurrentUser Account user,
            @RequestBody TaskRequestDto body
    ) {

        ClaimService.saveClaim(user, body);
    }


    @GetMapping("/{id}")
    public ResponseEntity getClaim(
            @CurrentUser Account user,
            @PathVariable("id") Long id
    ) {

        ClaimResponse response =  ClaimService.getClaim(user,id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}/accept")
    public String acceptClaim(
            @CurrentUser Account user,
            @PathVariable("id") Long id
    ) {

        ClaimService.acceptClaim(user,id);
        return "Hello World";
    }
}
