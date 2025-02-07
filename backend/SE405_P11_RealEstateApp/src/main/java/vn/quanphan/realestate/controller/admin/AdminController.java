package vn.quanphan.realestate.controller.admin;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin")
public class AdminController {

    @GetMapping("")
    public ResponseEntity<?> getModerfuckingTest() {
        return ResponseEntity.ok("test: ");
    }

}
