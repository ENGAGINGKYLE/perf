package mntm.top.offer.test;

public interface BaseErrorCode {

    ErrorCode PARAM_NULL_ERROR = new ErrorCode(10, "参数不能为空");

    ErrorCode ID_NULL_ERROR = new ErrorCode(11, "id不能为空");

    ErrorCode UID_NULL_ERROR = new ErrorCode(12, "public");

    ErrorCode UID_NOT_EXIST = new ErrorCode(13, "uid不存在");

    ErrorCode DATE_FORMAT_ERROR = new ErrorCode(15, "日期转换异常");

    ErrorCode PARAM_TYPE_ERROR = new ErrorCode(16, "参数类型错误");

    ErrorCode LACK_OF_PARAM_ERROR = new ErrorCode(17, "缺少请求参数");

    ErrorCode PARAM_BINDING_ERROR = new ErrorCode(18, "参数绑定失败");

    ErrorCode APP_KEY_NULL_ERROR = new ErrorCode(19, "appKey不能为空");

    ErrorCode ID_NOT_VALID = new ErrorCode(20, "ID 无效");

    ErrorCode SIGN_ERROR = new ErrorCode(21, "验签失败");

    ErrorCode IP_WHITELIST_ERROR = new ErrorCode(22, "ip白名单校验失败");

    ErrorCode PUBLIC_KEY_EMPTY = new ErrorCode(23, "公钥为空");

    ErrorCode SIGN_EMPTY = new ErrorCode(24, "验签签名为空");

    ErrorCode PARAM_NOT_EXIST = new ErrorCode(25, "参数不存在");

    ErrorCode INVOCATION_FAIL = new ErrorCode(26, "对象转换异常");

    ErrorCode REMOTE_ERROR = new ErrorCode(27, "远程调用失败");

    ErrorCode NO_QUOTAS = new ErrorCode(900019, "余额不足");

    ErrorCode SYSTEM_EXCEPTION = new ErrorCode(-1, "系统异常");

    ErrorCode FAIL = new ErrorCode(-2, "失败");

    ErrorCode PARAM_INVALID_ERROR = new ErrorCode(-3, "参数错误");

    ErrorCode AUTH_FAIL = new ErrorCode(0, "身份校验失败，请重新登录");

    ErrorCode PARAM_NOT_VALID = new ErrorCode(-9999, "参数错误");

}
