package hello.login;

import com.fasterxml.classmate.MemberResolver;
import hello.login.domain.item.Item;
import hello.login.domain.item.ItemRepository;
import hello.login.domain.member.Member;
import hello.login.domain.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class TestDataInit {

    private final ItemRepository itemRepository;
    private final MemberRepository memberRepository;


    /**
     * 테스트용 데이터 추가
     */
    @PostConstruct
    public void init() {
        itemRepository.save(new Item("itemA", 10000, 10));
        itemRepository.save(new Item("itemB", 20000, 20));

        Member member1 = new Member();
        member1.setLoginId("test1");
        member1.setPassword("test1!");
        member1.setName("테스터1");


        Member member2 = new Member();
        member2.setLoginId("test2");
        member2.setPassword("test2!");
        member2.setName("테스터2");

        memberRepository.save(member1);
        memberRepository.save(member2);
    }

}