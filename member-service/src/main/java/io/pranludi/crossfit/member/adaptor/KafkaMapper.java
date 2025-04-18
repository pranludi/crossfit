package io.pranludi.crossfit.member.adaptor;

import io.pranludi.crossfit.member.domain.MemberEntity;
import io.pranludi.crossfit.protobuf.kafka.MemberKafka;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface KafkaMapper {

    KafkaMapper INSTANCE = Mappers.getMapper(KafkaMapper.class);

    MemberKafka memberEntityToProto(MemberEntity member);

}
