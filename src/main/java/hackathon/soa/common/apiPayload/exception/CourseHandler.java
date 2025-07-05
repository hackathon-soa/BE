package hackathon.soa.common.apiPayload.exception;

import hackathon.soa.common.apiPayload.code.BaseErrorCode;


public class CourseHandler extends GeneralException {
    public CourseHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}