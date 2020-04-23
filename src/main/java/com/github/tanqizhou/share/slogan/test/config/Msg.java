package com.github.tanqizhou.share.slogan.test.config;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * @Auther: TanqiZhou
 * @Date: 2020/04/22/16:47
 * @Description:
 */
@ToString
@NoArgsConstructor
@Data
@Deprecated
public class Msg<T> implements Serializable{

    private static final long serialVersionUID = 4268801052358035098L;

    private T data;

    public T getData() {
        return data;
    }

    @JsonIgnore
    private ObjectMapper objectMapper = new ObjectMapper();

    private String stringData;

    public Msg(String stringData) {
        this.stringData = stringData;
    }

    public T  getData(Class<?>... parameterClasses){
        try {
            JavaType javaType = objectMapper.getTypeFactory().constructParametricType(Msg.class, parameterClasses);
            Msg<T> msg = objectMapper.readValue(stringData, javaType);
            this.data =  msg.data;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }
}
