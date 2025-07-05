package hackathon.soa.common.apiPayload.exception;

import hackathon.soa.common.apiPayload.code.BaseErrorCode;


public class SegmentHandler extends GeneralException {
    public SegmentHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}