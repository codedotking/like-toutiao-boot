package com.hongyan.toutiao.model.res;


import lombok.Data;

import java.io.Serializable;

@Data
public class ResultModel<T> implements Serializable {
    /**
     * 状态码
     */
    private String code;
    /**
     * 返回结果
     */
    private T data;
    /**
     * 返回消息
     */
    private String msg;

    /**
     * 创建一个表示成功操作的结果模型
     * 此方法用于在操作成功时，返回一个标准化的成功结果模型对象，该对象包含一个表示成功状态的代码
     *
     * @param <T> 泛型参数，表示结果模型中数据的类型，此处方法不使用具体类型，但提供类型灵活性
     * @return 返回一个成功状态的ResultModel对象，其中包含成功状态码
     */
    public static <T> ResultModel<T> success() {
        // 创建一个泛型ResultModel对象，用于封装操作结果
        ResultModel<T> resultModel = new ResultModel<>();
        // 设置结果模型的成功状态码
        resultModel.setCode(ResultCode.SUCCESS.getCode());
        // 返回成功状态的结果模型
        return resultModel;
    }

    /**
     * 创建一个表示成功操作的结果模型
     * 该方法用于封装成功的结果数据，将其与成功状态码一起返回
     *
     * @param data 成功操作后返回的数据，可以是任意类型
     * @return 返回一个包含成功状态码和相应数据的ResultModel对象
     */
    public static <T> ResultModel<T> success(T data) {
        // 创建一个ResultModel对象实例，用于封装结果数据和状态码
        ResultModel<T> ResultModel = new ResultModel<>();
        // 设置结果模型的数据为方法参数传入的数据
        ResultModel.setData(data);
        // 设置结果模型的状态码为成功状态码
        ResultModel.setCode(ResultCode.SUCCESS.getCode());
        // 返回封装了成功状态码和数据的ResultModel对象
        return ResultModel;
    }

    /**
     * 创建一个表示失败操作的结果模型
     * 此方法用于快速构建一个结果模型对象，该对象表示操作失败，使用默认的内部错误代码和消息
     *
     * @param <T> ResultModel中数据的泛型类型
     * @return 返回一个表示操作失败的ResultModel对象，包含错误代码和消息
     */
    public static <T> ResultModel<T> fail() {
        // 创建一个ResultModel对象实例
        ResultModel<T> ResultModel = new ResultModel<>();
        // 设置结果模型的错误代码为内部错误代码
        ResultModel.setCode(ResultCode.INTERNAL_ERROR.getCode());
        // 设置结果模型的错误消息为内部错误消息
        ResultModel.setMsg(ResultCode.INTERNAL_ERROR.getMsg());
        // 返回构建好的表示操作失败的ResultModel对象
        return ResultModel;
    }

    /**
     * 创建一个表示失败操作的结果模型
     * 此方法用于快速生成一个带有错误信息的结果模型对象，用于响应操作失败的情况
     * 它将ResultCode中的状态码和消息设置到ResultModel中，便于统一错误处理和信息返回
     *
     * @param resultCode 操作结果的状态码和错误信息，封装在ResultCode枚举中
     * @param <T>        泛型参数，表示ResultModel可以携带的业务数据类型，此处仅用于保持API一致性，实际并不使用
     * @return 返回一个表示操作失败的ResultModel对象，包含错误状态码和信息
     */
    public static <T> ResultModel<T> fail(ResultCode resultCode) {
        ResultModel<T> ResultModel = new ResultModel<>();
        ResultModel.setCode(resultCode.getCode());
        ResultModel.setMsg(resultCode.getMsg());
        return ResultModel;
    }


    /**
     * 创建一个表示失败结果的ResultModel对象
     * 此方法用于当操作失败时，返回一个携带错误信息的ResultModel实例
     * 它设置了错误代码为INTERNAL_ERROR，并接受一个字符串参数作为错误信息
     *
     * @param msg 错误信息，描述失败的原因
     * @return 返回一个失败的ResultModel对象，包含错误代码和错误信息
     */
    public static <T> ResultModel<T> fail(String msg) {
        // 初始化ResultModel对象，用于封装操作结果
        ResultModel<T> ResultModel = new ResultModel<>();
        // 设置错误代码为内部错误
        ResultModel.setCode(ResultCode.INTERNAL_ERROR.getCode());
        // 设置错误信息
        ResultModel.setMsg(msg);
        // 返回封装了错误信息的ResultModel对象
        return ResultModel;
    }
}