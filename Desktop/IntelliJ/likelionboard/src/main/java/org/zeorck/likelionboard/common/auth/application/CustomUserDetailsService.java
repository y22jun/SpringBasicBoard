package org.zeorck.likelionboard.common.auth.application;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.zeorck.likelionboard.common.auth.domain.CustomUserDetails;
import org.zeorck.likelionboard.domain.member.domain.Member;
import org.zeorck.likelionboard.domain.member.infrastructure.MemberRepository;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Long memberId = Long.valueOf(username);
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with id: " + memberId));

        return new CustomUserDetails(member.getId());
    }
}
