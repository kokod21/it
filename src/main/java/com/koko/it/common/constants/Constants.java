package com.koko.it.common.constants;

public interface Constants {
    //200: 请求成功
    //-1: 请求失败
    //401 Unauthorized: 用户未认证，请求失败
    //403 Forbidden: 用户无权限访问该资源，请求失败
    //429 Too Many Requests: 因为访问频繁，你已经被限制访问，稍后重试
    //500 Internal Server Error: 服务器错误，确认状态并报告问题
    interface Response {
        int SUCCESS = 0; //成功
        int FAIL = -1; //失败
        int SERVER_ERROR = 500; //服务器错误
        int AUTHORIZED_ERROR = 401; //未登录
        int FORBIDDEN    = 403; //禁止访问
        int MANY_REQUEST = 429; //访问频繁
        String SUCCESS_MSG = "";

        String SERVER_ERROR_MSG = "服务器错误";
        String AUTHORIZED_ERROR_MSG = "未登录";
        String FORBIDDEN_MSG = "禁止访问资源";
        String MANY_REQUEST_MSG = "访问过于频繁";

        String LOAD_DATA_FAILED = "获取数据出错";
    }

    int PAGE_SIZE = 20; // 分页加载，每页的数量

}
