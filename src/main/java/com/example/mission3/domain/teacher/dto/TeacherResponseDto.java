package com.example.mission3.domain.teacher.dto;

import com.example.mission3.domain.teacher.entity.Teacher;
import lombok.Getter;

public class TeacherResponseDto {

    @Getter
    public static class CreateTeacherResponseDto {

        private final String name;
        private final Integer career;
        private final String company;
        private final String phone;
        private final String introduction;

        public CreateTeacherResponseDto(Teacher teacher) {
            this.name = teacher.getName();
            this.career = teacher.getCareer();
            this.company = teacher.getCompany();
            this.phone = teacher.getPhone();
            this.introduction = teacher.getIntroduction();
        }
    }

    @Getter
    public static class EditTeacherResponseDto {

        private final Integer career;
        private final String company;
        private final String phone;
        private final String introduction;

        public EditTeacherResponseDto(Teacher teacher) {
            this.career = teacher.getCareer();
            this.company = teacher.getCompany();
            this.phone = teacher.getPhone();
            this.introduction = teacher.getIntroduction();
        }
    }

    @Getter
    public static class GetTeacherResponseDto {

        private final Long id;
        private final String name;
        private final Integer career;
        private final String company;
        private final String phone;
        private final String introduction;
        private final Boolean isDeleted;

        public GetTeacherResponseDto(Teacher teacher) {
            this.id = teacher.getId();
            this.name = teacher.getName();
            this.career = teacher.getCareer();
            this.company = teacher.getCompany();
            this.phone = teacher.getPhone();
            this.introduction = teacher.getIntroduction();
            this.isDeleted = teacher.getIsDeleted();
        }
    }
}
