package cn.kgc.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.TimeZone;

@Configuration
public class JacksonConfig {

    // 定义要使用的日期格式
    private static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    // 创建 LocalDateTime 序列化器
    private static final LocalDateTimeSerializer LOCAL_DATETIME_SERIALIZER =
            new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(DATE_TIME_FORMAT));

    // 创建 LocalDateTime 反序列化器
    private static final LocalDateTimeDeserializer LOCAL_DATETIME_DESERIALIZER =
            new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern(DATE_TIME_FORMAT));

    // 配置 Jackson 全局序列化规则
    @Bean
    public Jackson2ObjectMapperBuilder jackson2ObjectMapperBuilder() {
        return new Jackson2ObjectMapperBuilder()
                .serializers(LOCAL_DATETIME_SERIALIZER) // 注册自定义序列化器
                .deserializers(LOCAL_DATETIME_DESERIALIZER) // 注册自定义反序列化器
                .serializationInclusion(JsonInclude.Include.NON_NULL) // 忽略 null 值
//                .dateFormat(new SimpleDateFormat(DATE_TIME_FORMAT)) // 设置日期格式, 同时支持Date类型
                .simpleDateFormat(DATE_TIME_FORMAT) // 设置日期格式, 同时支持Date类型
                .timeZone(TimeZone.getTimeZone("Asia/Shanghai")); // 明确指定东八区
    }
}