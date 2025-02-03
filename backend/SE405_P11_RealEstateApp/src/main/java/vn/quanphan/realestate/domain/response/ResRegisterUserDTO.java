package vn.quanphan.realestate.domain.response;

import java.time.Instant;
import java.util.UUID;

@lombok.Getter
@lombok.Setter
public class ResRegisterUserDTO {

    private UUID id;
    private String fullName;
    private String email;
    private String phoneNumber;
    private Instant createdAt;

}
