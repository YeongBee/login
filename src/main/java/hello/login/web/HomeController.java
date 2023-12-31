package hello.login.web;

import hello.login.domain.member.Member;
import hello.login.domain.member.MemberRepository;
import hello.login.web.session.SessionManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {

    private final MemberRepository memberRepository;
    private final SessionManager sessionManager;


//    @GetMapping("/")
    public String home() {
        return "home";
    }

//    @GetMapping("/")
    public String homeLogin(@CookieValue(name = "memberId", required = false) Long memberId, Model model){
        if(memberId == null){
            return "home";
        }

        Member id = memberRepository.findById(memberId);
        if(id==null){
            return "home";
        }

        model.addAttribute("member", id);
        return "loginHome";
    }

//    @GetMapping("/")
    public String homeLogin2(HttpServletRequest request,Model model){
        // 세션 관리자에 저장된 회원 정보 조회
        Object session = sessionManager.getSession(request);

        if(session==null){
            return "home";
        }

        model.addAttribute("member", session);
        return "loginHome";
    }

//    @GetMapping("/")
    public String homeLogin3(HttpServletRequest request,Model model){

        HttpSession sess = request.getSession(false);


        // 세션 관리자에 저장된 회원 정보 조회
        Object session = sessionManager.getSession(request);

        if(session==null){
            return "home";
        }

        model.addAttribute("member", session);
        return "loginHome";
    }

    @GetMapping("/")
    public String homeLogin3Spring(@SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false)Member loginMember, Model model){

        if(loginMember==null){
            return "home";
        }

        model.addAttribute("member", loginMember);
        return "loginHome";
    }
}