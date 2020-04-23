package com.github.tanqizhou.share.slogan.test.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.converter.RecordMessageConverter;
import org.springframework.kafka.support.converter.StringJsonMessageConverter;

/**
 * @Auther: TanqiZhou
 * @Date: 2020/04/22/16:47
 * @Description:
 */
@Slf4j
@Configuration
public class KafkfaConfig {

    @Autowired
    ProducerFactory producerFactory;

    @Value("${spring.kafka.bootstrap-servers}")
    String bootstrapServersConfig;

    // @Bean
    // public KafkaAdmin kafkaAdmin() {
    //     Map<String, Object> configs = new HashMap<>();
    //     // 指定多个kafka集群多个地址，例如：192.168.2.11,9092,192.168.2.12:9092,192.168.2.13:9092
    //     configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG,bootstrapServersConfig);
    //     return new KafkaAdmin(configs);
    // }

    /**
     * 传输Value对象转成String，以支持KafKa的StringSerializer
     * @return
     */
    @Bean
    public RecordMessageConverter converter() {
        StringJsonMessageConverter converter = new StringJsonMessageConverter();
        // DefaultJackson2JavaTypeMapper typeMapper = new DefaultJackson2JavaTypeMapper();
        // typeMapper.setTypePrecedence(Jackson2JavaTypeMapper.TypePrecedence.INFERRED);
        // typeMapper.addTrustedPackages("com.github.tanqizhou.share.slogan.test.config");
        // Map<String, Class<?>> mappings = new HashMap<>();
        // mappings.put("msg", Msg.class);
        // typeMapper.setIdClassMapping(mappings);
        // converter.setTypeMapper(typeMapper);
        return converter;
    }

    @Bean
    public KafkaTemplate<String, Object> kafkaTemplate() {
        KafkaTemplate<String, Object> stringStringKafkaTemplate = new KafkaTemplate<String, Object>(producerFactory);
        stringStringKafkaTemplate.setDefaultTopic("topic.quick.default");
        stringStringKafkaTemplate.setMessageConverter(converter());
        return stringStringKafkaTemplate;
    }
}
