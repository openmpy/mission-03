package com.example.mission3.domain.teacher.dto;

import com.example.mission3.domain.teacher.entity.Teacher;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;

public class TeacherRequestDto {

    @Getter
    public static class CreateTeacherRequestDto {

        @Schema(description = "이름", example = "홍길동")
        @NotBlank(message = "이름을 입력해주세요.")
        private String name;

        @Schema(description = "경력(년차)", example = "1")
        @NotNull(message = "경력(년차)를 입력해주세요.")
        @PositiveOrZero(message = "경력(년차)를 다시 한번 확인해주세요.")
        private Integer career;

        @Schema(description = "회사", example = "항해99")
        @NotBlank(message = "회사를 입력해주세요.")
        private String company;

        @Schema(description = "전화번호", example = "01011112222")
        @NotBlank(message = "전화번호를 입력해주세요.")
        private String phone;

        @Schema(description = "소개", example = "코코넛 회사 출신입니다.")
        @NotBlank(message = "소개를 입력해주세요.")
        private String introduction;

        public Teacher toEntity() {
            return Teacher.builder()
                    .name(this.name)
                    .career(this.career)
                    .company(this.company)
                    .phone(this.phone)
                    .introduction(this.introduction)
                    .isDeleted(false)
                    .build();
        }
    }

    @Getter
    public static class EditTeacherRequestDto {

        @Schema(description = "경력(년차)", example = "10")
        @NotNull(message = "경력(년차)를 입력해주세요.")
        @PositiveOrZero(message = "경력(년차)를 다시 한번 확인해주세요.")
        private Integer career;

        @Schema(description = "회사", example = "항해100")
        @NotBlank(message = "회사를 입력해주세요.")
        private String company;

        @Schema(description = "전화번호", example = "01022223333")
        @NotBlank(message = "전화번호를 입력해주세요.")
        private String phone;

        @Schema(description = "소개", example = "옥수수 회사 출신입니다.")
        @NotBlank(message = "소개를 입력해주세요.")
        private String introduction;
    }
}
