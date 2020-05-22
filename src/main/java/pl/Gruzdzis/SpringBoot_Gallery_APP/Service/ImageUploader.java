package pl.Gruzdzis.SpringBoot_Gallery_APP.Service;


import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import pl.Gruzdzis.SpringBoot_Gallery_APP.Model.Image;
import pl.Gruzdzis.SpringBoot_Gallery_APP.Repository.ImageRepository;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Map;

@Service
public class ImageUploader {

    Cloudinary cloudinary;
    ImageRepository imageRepository;

    private final Logger logger = LoggerFactory.getLogger(ImageUploader.class);

    @Value("${app.upload.dir:${user.home}}")
    public String uploadDir;

    public ImageUploader(ImageRepository imageRepository) {
        cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "gallery-app-for-spring",
                "api_key", "**************",
                "api_secret", "**************"));

        this.imageRepository = imageRepository;
    }

    public void uploadFile(Path path){

        File fileToCloud = null;
        Map uploadResult = null;
        try{
            fileToCloud = new File(path.toString());
            uploadResult = cloudinary.uploader().upload(fileToCloud, ObjectUtils.emptyMap());

            String url = uploadResult.get("url").toString();
            imageRepository.save(new Image(url));

            Files.deleteIfExists(path);
        }
        catch (Exception e){
            //todo
        }
    }


    public Path getPath(MultipartFile file) {
        Path copyLocation = null;
        try {

            copyLocation = Paths
                    .get(uploadDir + File.separator + StringUtils.cleanPath(file.getOriginalFilename()));
            Files.copy(file.getInputStream(), copyLocation, StandardCopyOption.REPLACE_EXISTING);

        } catch (Exception e) {
            logger.info("Error");
        }
        return copyLocation;
    }


}