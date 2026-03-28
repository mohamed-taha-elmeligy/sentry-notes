package com.sentry.notes.controllers;

import com.sentry.notes.appservice.UserAppService;
import com.sentry.notes.dtos.request.UserRequest;
import com.sentry.notes.dtos.response.UserResponse;
import com.sentry.notes.security.userdetails.CustomUserDetails;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private UserAppService userAppService;
    private static final String NUMBER_OF_SIZE ="The page size can range from 10 to 20 records";
    private static final String NUMBER_OF_PAGE ="The number of pages can range from 1 to 10 pages";

    @GetMapping("/{numberOfPage}/{numberOfSize}")
    public ResponseEntity<Page<UserResponse>> getAllUsers(
            @PathVariable
            @Max(value = 10, message = NUMBER_OF_PAGE)
            @Min(value = 1, message = NUMBER_OF_PAGE)
            int numberOfPage,

            @PathVariable
            @Max(value = 20, message = NUMBER_OF_SIZE)
            @Min(value = 10, message = NUMBER_OF_SIZE)
            int numberOfSize){
        return ResponseEntity.ok(
                userAppService.getAllUsers(numberOfPage,numberOfSize)
        );
    }

    @GetMapping("/username")
    public ResponseEntity<UserResponse> findByUserName(
            @AuthenticationPrincipal CustomUserDetails customUserDetails
            ){
        return ResponseEntity.ok(
                userAppService.findByUserName(
                        customUserDetails.username()
                )
        );
    }

    @PutMapping("/update")
    public ResponseEntity<UserResponse> updateUser(
            @AuthenticationPrincipal CustomUserDetails customUserDetails,
            @Valid @RequestBody UserRequest userRequest){
        return ResponseEntity.ok(
                userAppService.updateUser(customUserDetails.userId(),userRequest)
        );
    }

    @DeleteMapping("/delete")
    public ResponseEntity<UserResponse> deleteUser(
            @AuthenticationPrincipal CustomUserDetails customUserDetails
    ){
        return ResponseEntity.ok(
                userAppService.deleteUser(
                        customUserDetails.userId()
                )
        );
    }

}
