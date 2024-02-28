package com.example.mission3.domain.lecture.entity;

import com.example.mission3.domain.lecture.entity.type.CategoryType;
import com.example.mission3.domain.teacher.entity.Teacher;
import com.example.mission3.global.entity.Timestamped;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@DynamicUpdate
@Entity
public class Lecture extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private Long price;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String introduction;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CategoryType category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_id", nullable = false)
    private Teacher teacher;

    public void update(String title, Long price, String introduction, CategoryType category) {
        if (title != null) {
            this.title = title;
        }
        if (price != null) {
            this.price = price;
        }
        if (introduction != null) {
            this.introduction = introduction;
        }
        if (category != null) {
            this.category = category;
        }
    }
}
