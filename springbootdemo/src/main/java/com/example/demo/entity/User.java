package com.example.demo.entity;


import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.example.demo.annotation.Secure;
import com.example.demo.annotation.SecureFlag;
import lombok.Data;
import lombok.experimental.Accessors;


/**
 * @ClassName: User
 * @Description: 
 * @Author: gaopengzhan
 * @Date: 2020-02-14 06:40:51
 * @Version: v1.0 文件初始创建
 */
@Data
@Accessors(chain = true)
@TableName("user")
@SecureFlag
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

    /**  */
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    /**  */
    @TableField("name")
    @Secure
    private String name;

    /**  */
    @TableField("phone")
    @Secure
    private String phone;

    /**  */
    @TableField("address")
    @Secure
    private String address;
}