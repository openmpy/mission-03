package com.example.mission3.domain.lecture.dto;

import com.example.mission3.domain.lecture.entity.Lecture;
import com.example.mission3.domain.lecture.entity.type.CategoryType;
import com.example.mission3.domain.teacher.entity.Teacher;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

public class LectureRequestDto {

    @Getter
    public static class CreateLectureRequestDto {

        @NotBlank(message = "강의명을 입력해주세요.")
        private String title;

        @NotNull(message = "가격을 입력해주세요.")
        private Long price;

        @NotBlank(message = "소개를 입력해주세요.")
        private String introduction;

        private CategoryType category;

        @NotNull(message = "강사 번호를 입력해주세요.")
        private Long teacherId;

        public Lecture toEntity(Teacher teacher) {
            return Lecture.builder()
                    .title(this.title)
                    .price(this.price)
                    .introduction(this.introduction)
                    .category(this.category)
                    .teacher(teacher)
                    .build();
        }
    }
}
