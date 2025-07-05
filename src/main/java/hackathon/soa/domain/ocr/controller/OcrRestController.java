package hackathon.soa.domain.ocr.controller;

import hackathon.soa.common.apiPayload.ApiResponse;
import hackathon.soa.common.apiPayload.code.status.SuccessStatus;
import hackathon.soa.domain.ocr.dto.OcrResponseDTO;
import hackathon.soa.domain.ocr.service.OcrService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequiredArgsConstructor
@RequestMapping("/ocr")
@Tag(name = "OCR", description = "OCR 관련 API")
public class OcrRestController {

    private final OcrService ocrService;

    @PostMapping(consumes = "multipart/form-data")
    @Operation(
            summary = "OCR 이미지 인식 및 text 변환",
            description = "이미지를 업로드하면 텍스트를 추출한 후 반환합니다."
    )
    public ApiResponse<OcrResponseDTO> processImage(
            @Parameter(
                    description = "이미지 파일 업로드",
                    content = @Content(mediaType = MediaType.APPLICATION_OCTET_STREAM_VALUE, schema = @Schema(type = "string", format = "binary"))
            )
            @RequestParam MultipartFile image
    ) {
        OcrResponseDTO result = ocrService.processImage(image);
        return ApiResponse.onSuccess(SuccessStatus._OK, result);
    }

}
