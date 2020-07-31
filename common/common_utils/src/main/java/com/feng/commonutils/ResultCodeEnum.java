package com.feng.commonutils;

import lombok.Getter;

/**
 * @Author shf
 * @Date 2020/7/8 14:47
 */
@Getter
public enum ResultCodeEnum {
    SUCCESS(true, 20000,"成功"),
    UNKNOWN_REASON(false, 20001, "未知错误"),
    BAD_SQL_GRAMMAR(false, 21001, "sql语法错误"),
    JSON_PARSE_ERROR(false, 21002, "json解析异常"),
    PARAM_ERROR(false, 21003, "参数不正确"),
    FILE_UPLOAD_ERROR(false, 21004, "文件上传失败"),
    FILE_DELETE_ERROR(false, 21005, "文件删除失败"),
    EXCEL_DATA_IMPORT_ERROR(false, 21006, "Excel数据导入错误"),
    SERVICE_INVOKE_ERROR(false, 21007, "服务调用失败");

    private final Boolean success;
    private final Integer code;
    private final String message;
    ResultCodeEnum(Boolean success, Integer code, String message) {
        this.success = success;
        this.code = code;
        this.message = message;
    }
}
