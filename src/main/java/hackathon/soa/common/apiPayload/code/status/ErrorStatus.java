package hackathon.soa.common.apiPayload.code.status;

import hackathon.soa.common.apiPayload.code.BaseErrorCode;
import hackathon.soa.common.apiPayload.code.ErrorReasonDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;


@Getter
@AllArgsConstructor
public enum ErrorStatus implements BaseErrorCode {

    // 가장 일반적인 응답
    _INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "COMMON500", "서버 에러, 관리자에게 문의 바랍니다."),
    _BAD_REQUEST(HttpStatus.BAD_REQUEST, "COMMON400", "잘못된 요청입니다."),
    _UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "COMMON401", "인증이 필요합니다."),
    _FORBIDDEN(HttpStatus.FORBIDDEN, "COMMON403", "금지된 요청입니다."),

    // 사용자 관련
    ID_DUPLICATED(HttpStatus.CONFLICT, "AUTH4001", "이미 사용중인 아이디입니다."),
    INVALID_PASSWORD(HttpStatus.UNAUTHORIZED, "AUTH4002", "비밀번호가 일치하지 않습니다."),
    ACCOUNT_NOT_APPROVED(HttpStatus.FORBIDDEN, "AUTH4003", "승인되지 않은 계정입니다."),


    // 사용자 관련
    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "MEMBER4001", "존재하지 않는 사용자입니다."),

    // 코스 관련
    NOT_FOUND_COURSE(HttpStatus.NOT_FOUND, "H001", "코스를 찾을 수 없습니다."),
    NOT_FOUND_SEGMENT(HttpStatus.NOT_FOUND, "H002", "세그먼트를 찾을 수 없습니다."),

    ;

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    @Override
    public ErrorReasonDTO getReason() {
        return ErrorReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(false)
                .build();
    }

    @Override
    public ErrorReasonDTO getReasonHttpStatus() {
        return ErrorReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(false)
                .httpStatus(httpStatus)
                .build();
    }
}
