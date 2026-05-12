package com.ga.deliverysystem.Repository;

import com.ga.deliverysystem.Model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface ImageRepository extends JpaRepository<Image, UUID> {
    Image findByUrl(String url);
}

