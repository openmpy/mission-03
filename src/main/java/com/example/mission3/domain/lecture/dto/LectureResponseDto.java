package com.example.mission3.domain.lecture.dto;

import com.example.mission3.domain.lecture.entity.Lecture;
import com.example.mission3.domain.lecture.entity.type.CategoryType;
import com.example.mission3.domain.teacher.dto.TeacherResponseDto.GetTeacherResponseDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

public class LectureResponseDto {

    @Getter
    public static class CreateLectureResponseDto {

        private final String title;
        private final Long price;
        private final String introduction;
        private final String category;
        private final Long teacherId;

        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
        private final LocalDateTime createdAt;

        public CreateLectureResponseDto(Lecture lecture) {
            this.title = lecture.getTitle();
            this.price = lecture.getPrice();
            this.introduction = lecture.getIntroduction();
            this.category = lecture.getCategory().name();
            this.teacherId = lecture.getTeacher().getId();
            this.createdAt = lecture.getCreatedAt();
        }
    }

    @Getter
    public static class EditLectureResponseDto {

        private final String title;
        private final Long price;
        private final String introduction;
        private final String category;

        public EditLectureResponseDto(Lecture lecture) {
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
        private final Boolean isDeleted;

        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
        private final LocalDateTime createdAt;

        private final GetTeacherResponseDto teacher;

        public GetLectureResponseDto(Lecture lecture) {
            this.id = lecture.getId();
            this.title = lecture.getTitle();
            this.price = lecture.getPrice();
            this.introduction = lecture.getIntroduction();
            this.category = lecture.getCategory();
            this.isDeleted = lecture.getIsDeleted();
            this.createdAt = lecture.getCreatedAt();
            this.teacher = new GetTeacherResponseDto(lecture.getTeacher());
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
