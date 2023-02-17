package com.dislike.backend.persistance.video.model;

import com.dislike.backend.persistance.user.model.UserPersistence;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DislikePersistence {

    @Id
    private String id;

    @ManyToOne
    private VideoPersistence videoPersistence;

    @ManyToOne
    private UserPersistence userPersistence;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        DislikePersistence that = (DislikePersistence) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
