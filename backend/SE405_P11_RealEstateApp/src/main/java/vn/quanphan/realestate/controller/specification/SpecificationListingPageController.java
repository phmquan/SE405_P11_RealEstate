package vn.quanphan.realestate.controller.specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import vn.quanphan.realestate.domain.SpecificationListingPage;
import vn.quanphan.realestate.domain.User;
import vn.quanphan.realestate.domain.request.specification.ReqSpecificationListingPage;
import vn.quanphan.realestate.domain.response.ResApiError;
import vn.quanphan.realestate.domain.response.ResSpecificationListingPageDTO;
import vn.quanphan.realestate.service.BrokerCertificationService;
import vn.quanphan.realestate.service.SpecificationListingPageService;
import vn.quanphan.realestate.service.UserService;
import vn.quanphan.realestate.util.SecurityUtil;
import vn.quanphan.realestate.util.error.IdInvalidException;

@RestController
@RequestMapping("/api/v1/specification")
@RequiredArgsConstructor
public class SpecificationListingPageController {

    private final SecurityUtil securityUtil;
    private final SpecificationListingPageService specificationListingPageService;
    private final UserService userService;
    private final BrokerCertificationService brokerCertificationService;

    @GetMapping("")
    public ResponseEntity<?> getSpecificationListingPage(
            @RequestParam("current") Optional<String> currentOptional,
            @RequestParam("pageSize") Optional<String> pageSizeOptional
    ) {
        String sCurrent = currentOptional.isPresent() ? currentOptional.get() : "";
        String sPageSize = pageSizeOptional.isPresent() ? pageSizeOptional.get() : "";
        int current = Integer.parseInt(sCurrent) - 1;
        int pageSize = Integer.parseInt(sPageSize);
        Pageable pageable = PageRequest.of(current, pageSize);
        List<SpecificationListingPage> specificationListingPages = specificationListingPageService.findSpecificationListingPages(pageable);
        List<ResSpecificationListingPageDTO> resSpecificationListingPageDTOs = new ArrayList<>();
        for (SpecificationListingPage specificationListingPage : specificationListingPages) {
            resSpecificationListingPageDTOs.add(specificationListingPageService.convertToResSpecificationListingPageDTO(specificationListingPage));
        }
        return !resSpecificationListingPageDTOs.isEmpty()
                ? ResponseEntity.ok(resSpecificationListingPageDTOs)
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResApiError(HttpStatus.NOT_FOUND.value(), "Không có trang đăng tin", System.currentTimeMillis()));
    }

    @GetMapping("/user")
    public ResponseEntity<?> getSpecificationListingPageCurrentUser(
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            return ResponseEntity.badRequest().body("Access Token không hợp lệ");
        }

        // Lấy access token từ header
        String accessToken = authorizationHeader.substring(7);
        // Giải mã access token
        Jwt decodedToken = this.securityUtil.checkValidAccessToken(accessToken);
        String email = decodedToken.getSubject();
        User currentUser = userService.getUserByEmail(email);
        if (currentUser.getSpecificationListingPage() == null) {
            ResApiError error = new ResApiError(HttpStatus.BAD_REQUEST.value(), "User chưa có trang đăng tin", System.currentTimeMillis());
            return ResponseEntity.badRequest().body(error);
        }
        SpecificationListingPage specificationListingPage = currentUser.getSpecificationListingPage();
        return ResponseEntity.ok(specificationListingPageService.convertToResSpecificationListingPageDTO(specificationListingPage));
    }

    @PostMapping("/create")
    public ResponseEntity<?> createSpecificationListingPage(
            @RequestBody ReqSpecificationListingPage requestSpecificationListingPage,
            @RequestHeader("Authorization") String authorizationHeader
    ) throws IdInvalidException {
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            return ResponseEntity.badRequest().body("Access Token không hợp lệ");
        }

        // Lấy access token từ header
        String accessToken = authorizationHeader.substring(7);
        // Giải mã access token
        Jwt decodedToken = this.securityUtil.checkValidAccessToken(accessToken);
        String email = decodedToken.getSubject();
        User currentUser = userService.getUserByEmail(email);
        if (currentUser.getSpecificationListingPage() != null) {
            ResApiError error = new ResApiError(HttpStatus.BAD_REQUEST.value(), "User đã có trang đăng tin / trang đăng tin đang chờ duyệt", System.currentTimeMillis());
            return ResponseEntity.badRequest().body(error);
        }
        if (brokerCertificationService.checkExistBrokerCertification(requestSpecificationListingPage.getBrokerCertification())) {
            ResApiError error = new ResApiError(HttpStatus.BAD_REQUEST.value(), "Chứng chỉ môi giới đã tồn tại", System.currentTimeMillis());
            return ResponseEntity.badRequest().body(error);
        }
        SpecificationListingPage specificationListingPage = specificationListingPageService.createSpecificationListingPage(requestSpecificationListingPage, currentUser);

        return ResponseEntity.ok(specificationListingPageService.convertToResSpecificationListingPageDTO(specificationListingPage));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getSpecificationListingPageById(
            @PathVariable("id") String id
    ) {
        SpecificationListingPage specificationListingPage = specificationListingPageService.getSpecificationListingPageById(id);
        return ResponseEntity.ok(specificationListingPageService.convertToResSpecificationListingPageDTO(specificationListingPage));
    }
}
