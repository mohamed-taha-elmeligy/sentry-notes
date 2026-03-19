package com.sentry.notes.controllers;


import com.sentry.notes.appservice.NoteAppService;
import com.sentry.notes.dtos.request.NoteRequest;
import com.sentry.notes.dtos.response.NoteResponse;
import com.sentry.notes.security.userdetails.CustomUserDetails;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/note")
public class NoteController {

    private NoteAppService noteAppService;
    private static final String NUMBER_OF_SIZE ="The page size can range from 10 to 20 records";
    private static final String NUMBER_OF_PAGE ="The number of pages can range from 1 to 10 pages";
    private static final String TITLE_CANNOT_NULL ="The title can not be null";


    @PostMapping("/create")
    public ResponseEntity<NoteResponse> createNote(@Valid @RequestBody NoteRequest noteRequest){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(noteAppService.createNote(noteRequest)
        );
    }

    @PutMapping("/update")
    public ResponseEntity<NoteResponse> updateNote(@Valid @RequestBody NoteRequest noteRequest){
        return ResponseEntity.ok(
                noteAppService.updateNote(noteRequest)
        );
    }

    @GetMapping("/userid/{numberOfPage}/{numberOfSize}")
    public ResponseEntity<Page<NoteResponse>> findByUserId(
            @AuthenticationPrincipal CustomUserDetails customUserDetails,

            @PathVariable
            @Max(value = 10, message = NUMBER_OF_PAGE)
            @Min(value = 1, message = NUMBER_OF_PAGE)
            int numberOfPage,

            @PathVariable
            @Max(value = 20, message = NUMBER_OF_SIZE)
            @Min(value = 10, message = NUMBER_OF_SIZE)
            int numberOfSize){
        return ResponseEntity.ok(
                noteAppService.findByUserId(customUserDetails.userId()
                        ,numberOfPage,numberOfSize
                )
        );
    }

    @GetMapping("/title/{title}")
    public ResponseEntity<NoteResponse> findByTitleAndUserId(
            @AuthenticationPrincipal CustomUserDetails customUserDetails,
            @PathVariable
            @NotBlank(message = TITLE_CANNOT_NULL)
            String title){
        return ResponseEntity.ok(
                noteAppService.findByTitleAndUserId(title,
                        customUserDetails.userId()
                )
        );
    }

    @DeleteMapping("/delete/{title}")
    public ResponseEntity<NoteResponse> deleteNote(
            @AuthenticationPrincipal CustomUserDetails customUserDetails,
            @PathVariable
            @NotBlank(message = TITLE_CANNOT_NULL)
            String title
    ){
        return ResponseEntity.ok(
                noteAppService.deleteNote(title,
                        customUserDetails.userId()
                )
        );
    }
}
