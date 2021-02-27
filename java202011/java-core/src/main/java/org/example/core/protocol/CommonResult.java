package org.example.core.protocol;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.core.constant.ResultEnum;

/**
 * @author tjstj
 * @description TODO
 * @date 2020/11/21 23:04
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommonResult<T extends CommonBean> {

    private int result = ResultEnum.Success.getResult();
    private long timestamp = System.currentTimeMillis();
    private String path;
    private String message = ResultEnum.Success.getMessage();
    private T param;
}
