package com.kingboy.common.tools.exception;

import com.kingboy.common.utils.json.JsonUtils;
import feign.Response;
import feign.Util;
import feign.codec.ErrorDecoder;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j;
import org.springframework.context.annotation.Configuration;

import static java.lang.String.format;

/**
 * @author kingboy--KingBoyWorld@163.com
 * @date 2017/11/17 下午4:09
 * @desc .
 */
@Configuration
@Log4j
public class FeignExceptionHandler implements ErrorDecoder {

    private static final PhantomException NULL_PHANTOM_EXCEPTION = new PhantomException();

    @Override
    @SneakyThrows
    public Exception decode(String methodKey, Response response) {
        if (response.body() != null) {
            String body = Util.toString(response.body().asReader());
            log.error(format("status:%s reading:%s body:%s", response.status(), methodKey, body));
            PhantomException exception = JsonUtils.jsonToBean(body, PhantomException.class);
            return exception;
        }
        return NULL_PHANTOM_EXCEPTION;
    }
}
