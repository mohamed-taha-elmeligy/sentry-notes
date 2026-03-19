package com.sentry.notes.controllers;

import com.sentry.notes.appservice.NoteAppService;
import com.sentry.notes.appservice.UserAppService;
import com.sentry.notes.dtos.request.UserRequest;
import com.sentry.notes.dtos.response.NoteResponse;
import com.sentry.notes.dtos.response.UserResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/public")
public class PublicController {

    private NoteAppService noteAppService;
    private UserAppService userAppService;
    private static final String NUMBER_OF_SIZE ="The page size can range from 10 to 20 records";
    private static final String NUMBER_OF_PAGE ="The number of pages can range from 1 to 10 pages";

    @GetMapping("/{numberOfPage}/{numberOfSize}")
    public ResponseEntity<Page<NoteResponse>> getPublicNotes(
            @PathVariable
            @Max(value = 10, message = NUMBER_OF_PAGE)
            @Min(value = 1, message = NUMBER_OF_PAGE)
            int numberOfPage,

            @PathVariable
            @Max(value = 20, message = NUMBER_OF_SIZE)
            @Min(value = 10, message = NUMBER_OF_SIZE)
            int numberOfSize){
        return ResponseEntity.ok(
                noteAppService.getPublicNotes(numberOfPage, numberOfSize)
        );
    }

    @PostMapping("/create-account")
    public ResponseEntity<UserResponse> createAccount(
            @Valid @RequestBody UserRequest userRequest
            )
    {
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(userAppService.createUser(userRequest)
        );
    }

}
