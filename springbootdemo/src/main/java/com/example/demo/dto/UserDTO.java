package com.example.demo.dto;


import javax.validation.constraints.NotNull;
import java.util.Date;
import java.io.Serializable;

import lombok.Data;


/**
 * @ClassName: UserDTO
 * @Description: ，与前端交互时使用
 * @Author: gaopengzhan
 * @Date: 2020-02-14 06:40:51
 * @Version: v1.0 文件初始创建
 */ 
@Data
public class UserDTO implements Serializable {

	private static final long serialVersionUID = 1L;

    /**  */
    @NotNull(message = "不能为空")
    private Integer id;

    /**  */
    private String name;

    /**  */
    private String phone;

    /**  */
    private String address;

}