package io.pranludi.crossfit.member.repository;

import io.pranludi.crossfit.member.repository.dto.MemberDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<MemberDTO, String> {

}
