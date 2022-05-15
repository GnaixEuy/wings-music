package cn.limitless.wingsmusic.handle;

import cn.limitless.wingsmusic.exception.BizException;
import cn.limitless.wingsmusic.exception.ExceptionType;
import cn.limitless.wingsmusic.vo.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

/**
 * <img src="http://blog.GnaixEuy.cn/wp-content/uploads/2021/08/bug.jpeg"/>
 *
 * <p>项目： wings-music </p>
 *
 * @author GnaixEuy
 * @date 2022/5/15
 * @see <a href="https://github.com/GnaixEuy"> GnaixEuy的GitHub </a>
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {BizException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse bizExceptionHandler(BizException bizException) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setCode(bizException.getCode());
        errorResponse.setMessage(bizException.getMessage());
        errorResponse.setTrace(bizException.getStackTrace());
        log.error(bizException.getMessage());
        return errorResponse;
    }

    @ExceptionHandler(value = {Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse exceptionHandler(Exception exception) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setCode(ExceptionType.INNER_ERROR.getCode());
        errorResponse.setMessage(ExceptionType.INNER_ERROR.getMessage());
        exception.printStackTrace();
        log.error(exception.getMessage());
        return errorResponse;
    }

    @ExceptionHandler(value = AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ErrorResponse accessDeniedHandler(Exception e) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setCode(ExceptionType.FORBIDDEN.getCode());
        errorResponse.setMessage(ExceptionType.FORBIDDEN.getMessage());
        e.printStackTrace();
        log.error(e.getMessage());
        return errorResponse;
    }


    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public List<ErrorResponse> bizExceptionHandler(MethodArgumentNotValidException e) {
        List<ErrorResponse> errorResponses = new ArrayList<>();
        e.printStackTrace();
        e.getBindingResult().getAllErrors().forEach((error) -> {
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setCode(ExceptionType.BAD_REQUEST.getCode());
            errorResponse.setMessage(error.getDefaultMessage());
            errorResponses.add(errorResponse);
            log.error(error.getDefaultMessage());
        });
        return errorResponses;
    }
}
