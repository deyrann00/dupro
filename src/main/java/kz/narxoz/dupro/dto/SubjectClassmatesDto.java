package kz.narxoz.dupro.dto;

import java.util.List;

public record SubjectClassmatesDto(
        String subjectName,
        String subjectCode,
        List<StudentDto> students
) {}
