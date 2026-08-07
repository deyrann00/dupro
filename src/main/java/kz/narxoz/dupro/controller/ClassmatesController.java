package kz.narxoz.dupro.controller;

import kz.narxoz.dupro.dto.SubjectClassmatesDto;
import kz.narxoz.dupro.service.ClassmatesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/curriculum")
@RequiredArgsConstructor
public class ClassmatesController {

    private final ClassmatesService classmatesService;

    @GetMapping("/{curriculumId}/classmates")
    public ResponseEntity<List<SubjectClassmatesDto>> getClassmates(
            @RequestHeader("Authorization") String token,
            @PathVariable String curriculumId
    ) {
        List<SubjectClassmatesDto> response = classmatesService.getClassmatesByCurriculum(token, curriculumId);
        return ResponseEntity.ok(response);
    }
}