package com.ga.deliverysystem.Service;


import com.ga.deliverysystem.Dto.request.ImageRequest;
import com.ga.deliverysystem.Model.Image;
import com.ga.deliverysystem.Repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImageService {

    @Autowired
    private CloudinaryService cloudinaryService;
    @Autowired
    private ImageRepository imageRepository;

    public String uploadImage(ImageRequest imageModel, String folder) {
        try {
            // if the name or file is missing return null
            if (imageModel.getName().isEmpty() || imageModel.getFile().isEmpty()) {
                return null;
            }

            // create image instance
            Image image = new Image();

            // upload the image
            var imageUrl = cloudinaryService.uploadFile(imageModel.getFile(), folder);

            // if no URL (failed to upload the image) return null
            if (imageUrl == null) {
                return null;
            }

            // set the image fields
            image.setName(imageModel.getName()); // set the image name
            image.setUrl(imageUrl); // set the image url

            // save the new entity
            imageRepository.save(image);

            // return the uploaded image url
            return image.getUrl();
        } catch (Exception e) {
            // log the error message
            System.err.println(e.getMessage());

            // if failed to upload the image return null
            return null;
        }


    }
}
