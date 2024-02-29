package com.example.mission3.domain.teacher.dto;

import com.example.mission3.domain.teacher.entity.Teacher;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;

public class TeacherRequestDto {

    @Getter
    public static class CreateTeacherRequestDto {

        @NotBlank(message = "이름을 입력해주세요.")
        private String name;

        @NotNull(message = "경력(년차)를 입력해주세요.")
        @PositiveOrZero(message = "경력(년차)를 다시 한번 확인해주세요.")
        private Integer career;

        @NotBlank(message = "회사를 입력해주세요.")
        private String company;

        @NotBlank(message = "전화번호를 입력해주세요.")
        private String phone;

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

        @NotNull(message = "경력(년차)를 입력해주세요.")
        @PositiveOrZero(message = "경력(년차)를 다시 한번 확인해주세요.")
        private Integer career;

        @NotBlank(message = "회사를 입력해주세요.")
        private String company;

        @NotBlank(message = "전화번호를 입력해주세요.")
        private String phone;

        @NotBlank(message = "소개를 입력해주세요.")
        private String introduction;
    }
}
