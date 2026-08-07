package kz.narxoz.dupro.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "university-api", url = "https://du.narxoz.kz/api")
public interface UniversityApiClient {

    @GetMapping("/individual-curriculums/{curriculumId}/subjects")
    String getRawSubjectsData(
            @RequestHeader("Authorization") String bearerToken,
            @PathVariable("curriculumId") String curriculumId
    );
}
