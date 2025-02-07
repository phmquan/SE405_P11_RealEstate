package vn.quanphan.realestate.domain.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class ResApiError {

    private int status;
    private String message;
    private long timestamp;

}
