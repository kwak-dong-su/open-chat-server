package com.kds.openchatserver.api.config.message

import com.kds.openchatserver.api.domain.vo.ChatVO
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.kafka.common.serialization.StringSerializer
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.core.ConsumerFactory
import org.springframework.kafka.core.DefaultKafkaConsumerFactory
import org.springframework.kafka.core.DefaultKafkaProducerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.core.ProducerFactory
import org.springframework.kafka.support.serializer.JsonDeserializer
import org.springframework.kafka.support.serializer.JsonSerializer

@Configuration
class KafkaConfig {

    @Value("\${spring.kafka.producer.bootstrap-servers}")
    lateinit var producerGroup: String

    @Value("\${spring.kafka.consumer.bootstrap-servers}")
    lateinit var consumerGroup: String

    @Value("\${spring.kafka.consumer.group-id}")
    lateinit var consumerGroupId: String


    @Bean
    fun producerFactory(): ProducerFactory<String, Any> =
        HashMap<String, Any>().also {
            it[ProducerConfig.BOOTSTRAP_SERVERS_CONFIG] = producerGroup
            it[ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG] = StringSerializer::class.java
            it[ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG] = JsonSerializer::class.java
        }.run { DefaultKafkaProducerFactory(this, StringSerializer(), JsonSerializer()) }

    @Bean
    fun consumerFactory(): ConsumerFactory<String, Any> =
        HashMap<String, Any>().also {
            it[ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG] = consumerGroup
            it[ConsumerConfig.GROUP_ID_CONFIG] = consumerGroupId
            it[ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG] = StringDeserializer::class.java
            it[ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG] = JsonDeserializer::class.java
            it[JsonDeserializer.TRUSTED_PACKAGES] = "*"
        }.run { DefaultKafkaConsumerFactory(this, StringDeserializer(), JsonDeserializer()) }

    @Bean
    fun kafkaTemplate(): KafkaTemplate<String, Any> = KafkaTemplate(producerFactory())

    @Bean
    fun kafkaListenerContainerFactory(): ConcurrentKafkaListenerContainerFactory<String, ChatVO> =
        ConcurrentKafkaListenerContainerFactory<String, ChatVO>().also {
            it.consumerFactory = consumerFactory()
            it.containerProperties.isMissingTopicsFatal = false
        }
}