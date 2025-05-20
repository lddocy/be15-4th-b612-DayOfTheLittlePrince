package littleprince.member.query.controller;

import littleprince.member.query.service.MemberQueryService;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberQueryController {

    private final MemberQueryService memberQueryService;
}
