package com.steam.discount.config;

import com.steam.discount.model.ResultVO;
import com.steam.discount.util.JsonUtil;
import com.steam.discount.util.ResultVoUtil;
import jakarta.validation.constraints.NotNull;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@ControllerAdvice
public class ResponseResultAdvice implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(@Nullable MethodParameter returnType, @Nullable Class<? extends HttpMessageConverter<?>> converterType) {

        return true;
    }

    @Nullable
    @Override
    public Object beforeBodyWrite(@Nullable Object body, @Nullable MethodParameter returnType, @Nullable MediaType selectedContentType,
                                  @Nullable Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                  @NotNull ServerHttpRequest request, @Nullable ServerHttpResponse response) {
        String path = request.getURI().getPath();
        if (isSwaggerRequest(path)) {
            return body;
        }

        if (body instanceof String) {
            return JsonUtil.toJson(ResultVoUtil.success(body));
        }

        if (body instanceof ResultVO<?>) {
            return body;
        }
        return ResultVoUtil.success(body);
    }

    private boolean isSwaggerRequest(String path) {
        return path.startsWith("/swagger")
                || path.startsWith("/v3/api-docs")
                || path.startsWith("/v2/api-docs")
                || path.startsWith("/doc.html")
                || path.startsWith("/swagger-resources")
                || path.startsWith("/webjars");
    }
}
