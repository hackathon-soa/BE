package hackathon.soa.domain.test;

import hackathon.soa.domain.test.dto.TestResponseDTO;

public class TestConverter {

    public static TestResponseDTO.TestDTO toTestDTO(){
        return TestResponseDTO.TestDTO.builder()
                .testString("테스트")
                .build();
    }
}
