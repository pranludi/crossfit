package io.pranludi.crossfit.member.adaptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.pranludi.crossfit.member.domain.MemberEntity;
//import io.pranludi.crossfit.protobuf.kafka.MemberKafka;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class MemberProducer {

    final Logger log = LoggerFactory.getLogger(MemberProducer.class);
    final String TOPIC = "members";
    final KafkaTemplate<String, byte[]> kafkaTemplate;
    final ObjectMapper objectMapper;

    public MemberProducer(KafkaTemplate<String, byte[]> kafkaTemplate, ObjectMapper objectMapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

    public void sendNewMember(MemberEntity member) {
//        MemberKafka memberKafka = KafkaMapper.INSTANCE.memberEntityToProto(member);
//        kafkaTemplate.send(TOPIC, "member", memberKafka.toByteArray());
    }

}