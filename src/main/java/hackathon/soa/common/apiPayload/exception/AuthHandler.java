package hackathon.soa.common.apiPayload.exception;

import hackathon.soa.common.apiPayload.code.BaseErrorCode;


public class AuthHandler extends GeneralException {
    public AuthHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}