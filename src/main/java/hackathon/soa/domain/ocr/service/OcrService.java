package hackathon.soa.domain.ocr.service;

import hackathon.soa.domain.ocr.dto.OcrResponseDTO;
import org.springframework.web.multipart.MultipartFile;

public interface OcrService {
    OcrResponseDTO processImage(MultipartFile image);
}
