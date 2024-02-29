package com.example.mission3.domain.lecture.dto;

import com.example.mission3.domain.lecture.entity.Lecture;
import com.example.mission3.domain.lecture.entity.type.CategoryType;
import com.example.mission3.domain.teacher.entity.Teacher;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;

public class LectureRequestDto {

    @Getter
    public static class CreateLectureRequestDto {

        @Schema(description = "강의명", example = "Spring 완전 정복")
        @NotBlank(message = "강의명을 입력해주세요.")
        private String title;

        @Schema(description = "가격", example = "100000")
        @NotNull(message = "가격을 입력해주세요.")
        @PositiveOrZero(message = "가격을 다시 한번 확인해주세요.")
        private Long price;

        @Schema(description = "소개", example = "초보자 대상")
        @NotBlank(message = "소개를 입력해주세요.")
        private String introduction;

        private CategoryType category;

        @Schema(description = "강사 번호", example = "1")
        @NotNull(message = "강사 번호를 입력해주세요.")
        private Long teacherId;

        public Lecture toEntity(Teacher teacher) {
            return Lecture.builder()
                    .title(this.title)
                    .price(this.price)
                    .introduction(this.introduction)
                    .category(this.category)
                    .teacher(teacher)
                    .isDeleted(false)
                    .build();
        }
    }

    @Getter
    public static class EditLectureRequestDto {

        @Schema(description = "강의명", example = "Spring 완전 정복2")
        @NotBlank(message = "강의명을 입력해주세요.")
        private String title;

        @Schema(description = "가격", example = "200000")
        @NotNull(message = "가격을 입력해주세요.")
        @PositiveOrZero(message = "가격을 다시 한번 확인해주세요.")
        private Long price;

        @Schema(description = "소개", example = "중급자 대상")
        @NotBlank(message = "소개를 입력해주세요.")
        private String introduction;

        private CategoryType category;
    }
}
