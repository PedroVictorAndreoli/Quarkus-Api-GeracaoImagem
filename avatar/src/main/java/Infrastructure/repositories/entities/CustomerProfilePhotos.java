package Infrastructure.repositories.entities;

import domain.models.Customer;
import domain.models.ProfilePhoto;
import jakarta.persistence.*;

import java.awt.*;
import java.util.List;
import java.util.UUID;

@Entity(name = "profile_photos")
public class CustomerProfilePhotos {
    @EmbeddedId
    CompositeKey compositeKey;

    @Column(name = "original_photo")
    String originalPhoto;

    @Column(name = "generated_photo")
    String generatedPhoto;



    @Embeddable
    public static class CompositeKey {
        @Column(name = "customer_id")
        String customerId;

        @Column(name = "id")
        String id;
    }


    public Customer toDomain() {
        return new Customer(compositeKey.customerId, List.of(new ProfilePhoto(compositeKey.id,
                originalPhoto,
                generatedPhoto)));
    }

    public static CustomerProfilePhotos fromDomain(String customerId,ProfilePhoto profilePhoto){
        var entity = new CustomerProfilePhotos();

        entity.compositeKey = new CompositeKey();
        entity.compositeKey.customerId = customerId;
        entity.compositeKey.id = profilePhoto.id().toString();

        entity.originalPhoto = profilePhoto.originalPhoto();
        entity.generatedPhoto = profilePhoto.generetedPhoto();


        return entity;
    }
}
