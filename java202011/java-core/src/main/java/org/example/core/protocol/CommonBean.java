package org.example.core.protocol;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author tjstj
 * @description TODO
 * @date 2020/11/22 17:28
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonBean implements Serializable {
    private String code;
    private String message;
}
