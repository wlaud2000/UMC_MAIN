package com.example.study.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

public class StortRequestDTO {

    @Getter
    @Setter
    public class AddStoreRequestDto {

        @NotBlank(message = "가게 이름은 필수입니다.")
        private String name;

        @NotBlank(message = "가게 주소는 필수입니다.")
        private String address;

        @NotNull(message = "지역 ID는 필수입니다.")
        private Long regionId;
    }

}
