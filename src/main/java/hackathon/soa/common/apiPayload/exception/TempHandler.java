package hackathon.soa.common.apiPayload.exception;


import hackathon.soa.common.apiPayload.code.BaseErrorCode;

public class TempHandler extends GeneralException {

    public TempHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
