package littleprince.item.command.application.controller;

import littleprince.item.command.application.service.ItemCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/item")
public class ItemCommandController {
    private final ItemCommandService itemCommandService;
}
