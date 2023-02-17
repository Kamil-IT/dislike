package com.dislike.backend.persistance.video.model;

import com.dislike.backend.bussines.model.VideoDataModification;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class VideoPersistence {

    @Id
    private String id;
    private String name;

    @OneToMany(cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<DislikePersistence> dislikePersistences = new ArrayList<>();

    public static VideoPersistence of(VideoDataModification videoDataModification) {
        return new VideoPersistence(videoDataModification.id(), videoDataModification.username(), new ArrayList<>());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        VideoPersistence that = (VideoPersistence) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
