package hackathon.soa.common.securitry;

import hackathon.soa.domain.member.MemberRepository;
import hackathon.soa.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String memberId) throws UsernameNotFoundException {
        Member member = memberRepository.findById(Long.parseLong(memberId))
                .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다: " + memberId));

        return new CustomUserDetails(member);
    }
}
