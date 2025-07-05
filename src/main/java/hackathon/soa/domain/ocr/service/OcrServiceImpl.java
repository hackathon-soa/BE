package hackathon.soa.domain.ocr.service;

import hackathon.soa.domain.ocr.client.ClovaOcrClient;
import hackathon.soa.domain.ocr.dto.OcrResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class OcrServiceImpl implements OcrService {

    private final ClovaOcrClient clovaOcrClient; // CLOVA OCR API를 호출하는 클라이언트

    @Override
    public OcrResponseDTO processImage(MultipartFile image) {
        String rawText = clovaOcrClient.extractText(image); // 1. CLOVA OCR을 이용해 이미지에서 텍스트 추출
        return new OcrResponseDTO(rawText);
    }
}

