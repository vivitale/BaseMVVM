package com.sendinfo.posycy.entitys

import java.io.Serializable

/**
 * 作用: 基础请求参数类
 * 作者：赵小白 email:vvtale@gmail.com  
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
class BaseRequest : Serializable
{
    var id: String? = null
    var loginName: String? = null
    var password: String? = null
    var machineCode: String? = null

    var key: String? = null
    var city: String? = null
}
