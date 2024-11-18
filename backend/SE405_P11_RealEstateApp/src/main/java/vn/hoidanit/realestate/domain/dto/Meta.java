package vn.hoidanit.realestate.domain.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Meta {
    private int page;
    private int pageSize;
    private int pages;
    private long totals;


    public void getPages(int totalPages) {

    }
}
