package com.example.mission3.domain.lecture.dto;

import com.example.mission3.domain.lecture.entity.Lecture;
import lombok.Getter;

public class LectureResponseDto {

    @Getter
    public static class CreateLectureResponseDto {

        private final String title;
        private final Long price;
        private final String introduction;
        private final String category;
        private final Long teacherId;

        public CreateLectureResponseDto(Lecture lecture) {
            this.title = lecture.getTitle();
            this.price = lecture.getPrice();
            this.introduction = lecture.getIntroduction();
            this.category = lecture.getCategory().name();
            this.teacherId = lecture.getTeacher().getId();
        }
    }
}
