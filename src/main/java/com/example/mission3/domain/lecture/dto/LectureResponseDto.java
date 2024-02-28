package com.example.mission3.domain.lecture.dto;

import com.example.mission3.domain.lecture.entity.Lecture;
import com.example.mission3.domain.lecture.entity.type.CategoryType;
import lombok.Getter;

import java.util.List;

public class LectureResponseDto {

    @Getter
    public static class CreateLectureResponseDto {

        private final Long id;
        private final String title;
        private final Long price;
        private final String introduction;
        private final String category;
        private final Long teacherId;

        public CreateLectureResponseDto(Lecture lecture) {
            this.id = lecture.getId();
            this.title = lecture.getTitle();
            this.price = lecture.getPrice();
            this.introduction = lecture.getIntroduction();
            this.category = lecture.getCategory().name();
            this.teacherId = lecture.getTeacher().getId();
        }
    }

    @Getter
    public static class EditLectureResponseDto {

        private final Long id;
        private final String title;
        private final Long price;
        private final String introduction;
        private final String category;

        public EditLectureResponseDto(Lecture lecture) {
            this.id = lecture.getId();
            this.title = lecture.getTitle();
            this.price = lecture.getPrice();
            this.introduction = lecture.getIntroduction();
            this.category = lecture.getCategory().name();
        }
    }

    @Getter
    public static class GetLectureResponseDto {

        private final Long id;
        private final String title;
        private final Long price;
        private final String introduction;
        private final CategoryType category;
        private final Long teacherId;

        public GetLectureResponseDto(Lecture lecture) {
            this.id = lecture.getId();
            this.title = lecture.getTitle();
            this.price = lecture.getPrice();
            this.introduction = lecture.getIntroduction();
            this.category = lecture.getCategory();
            this.teacherId = lecture.getTeacher().getId();
        }
    }

    @Getter
    public static class GetLectureFromTeacherResponseDto {

        private final Long teacherId;
        private final List<GetLectureResponseDto> lectures;

        public GetLectureFromTeacherResponseDto(Long teacherId, List<GetLectureResponseDto> lectures) {
            this.teacherId = teacherId;
            this.lectures = lectures;
        }
    }
}
