package vn.quanphan.realestate.domain.request.property;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "propertyType")
@JsonSubTypes({
    @JsonSubTypes.Type(value = HouseDTO.class, name = "house"),
    @JsonSubTypes.Type(value = LandDTO.class, name = "land"),
    @JsonSubTypes.Type(value = ApartmentDTO.class, name = "apartment")
})
public class PropertyDTO {

    private String address;
    private String district;
    private String propertyLength;
    private String propertyWidth;
    private String propertyArea;
    private String legalDocument;
    private String propertyPrice;
    private List<String> propertyImages;
}
