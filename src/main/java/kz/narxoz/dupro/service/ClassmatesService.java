package kz.narxoz.dupro.service;

import kz.narxoz.dupro.client.UniversityApiClient;
import kz.narxoz.dupro.dto.StudentDto;
import kz.narxoz.dupro.dto.SubjectClassmatesDto;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ClassmatesService {

    private final UniversityApiClient apiClient;
    private final ObjectMapper objectMapper;

    @SneakyThrows
    public List<SubjectClassmatesDto> getClassmatesByCurriculum(String token, String curriculumId) {
        String bearerToken = token.startsWith("Bearer ") ? token : "Bearer " + token;

        // 1. Запрос к API университета
        String rawJson = apiClient.getRawSubjectsData(bearerToken, curriculumId);
        JsonNode rootNode = objectMapper.readTree(rawJson);

        List<SubjectClassmatesDto> result = new ArrayList<>();

        if (!rootNode.isArray()) {
            return result;
        }

        // 2. Обходим каждый предмет в полученном массиве
        for (JsonNode subjectNode : rootNode) {
            String subjectName = subjectNode.path("subjectNameRu").asText();
            String subjectCode = subjectNode.path("code").asText();

            List<StudentDto> students = new ArrayList<>();
            JsonNode studentsArray = subjectNode.path("academicStream").path("students");

            if (studentsArray.isArray()) {
                for (JsonNode studentNode : studentsArray) {
                    String email = studentNode.path("email").asText();
                    String registeredAt = studentNode.path("createdAt").asText();

                    JsonNode userInfo = studentNode.path("userInfo");

                    String fullName = userInfo.path("fullName").asText();
                    if (fullName == null || fullName.isBlank()) {
                        String firstName = userInfo.path("firstName").asText();
                        String lastName = userInfo.path("lastName").asText();
                        fullName = (lastName + " " + firstName).trim();
                    }

                    students.add(new StudentDto(fullName, email, registeredAt));
                }
            }

            result.add(new SubjectClassmatesDto(subjectName, subjectCode, students));
        }

        return result;
    }
}