package io.pranludi.crossfit.member.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.pranludi.crossfit.member.domain.MemberGrade;
import io.pranludi.crossfit.member.repository.dto.MemberDTO;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

@DataJpaTest
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    @Transactional
    void save() {
        // given
        MemberDTO dto = new MemberDTO("id", "password", "branchId", "name", "email", "phoneNumber", MemberGrade.NORMAL, LocalDateTime.now());
        dto.setNew(true);

        // when
        MemberDTO saved = memberRepository.save(dto);

        // then
        assertEquals(dto.getId(), saved.getId());
    }

}