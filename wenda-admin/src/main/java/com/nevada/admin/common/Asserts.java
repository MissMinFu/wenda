package com.nevada.admin.common;

/**
 * @program:wenda
 * @description: 断言
 * @author: nevada
 * @create: 2020-04-13 17:21
 **/
public class Asserts {

    public static void fail(String message) {
        throw new ApiException(message);
    }

    public static void fail(IErrorCode errorCode) {
        throw new ApiException(errorCode);
    }
}
