package hello.servlet.domain.member;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

class MemberRepositoryTest {

	MemberRepository memberRepository = MemberRepository.getInstance();

	@AfterEach
	void afterEach() {
		memberRepository.clearStore();
	}

	@Test
	public void save() throws Exception {
		//given
		Member member = new Member("hello", 20);

		//when
		Member savedMember = memberRepository.save(member);

		//then
		Member findMember = memberRepository.findById(savedMember.getId());
		assertThat(findMember).isSameAs(savedMember);
	}

	@Test
	public void findAll() throws Exception {
		//given
		Member member1 = new Member("Karina", 20);
		Member member2 = new Member("Winter", 20);

		memberRepository.save(member1);
		memberRepository.save(member2);

		//when
		List<Member> result = memberRepository.findAll();

		//then
		assertThat(result.size()).isEqualTo(2);
		assertThat(result).contains(member1, member2);
	}
}