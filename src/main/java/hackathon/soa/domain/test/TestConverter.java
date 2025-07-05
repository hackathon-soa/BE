package hackathon.soa.domain.test;

import hackathon.soa.domain.test.dto.TestResponseDTO;

public class TestConverter {

    public static TestResponseDTO.TestDTO toTestDTO(){
        return TestResponseDTO.TestDTO.builder()
                .testString("테스트")
                .build();
    }
    public static TestResponseDTO.TestDTO toTempTestDTO(){
        return TestResponseDTO.TestDTO.builder()
                .testString("테스트 성공")
                .build();
    }

}
