package pl.Gruzdzis.SpringBoot_Gallery_APP.Api;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.Gruzdzis.SpringBoot_Gallery_APP.Model.Image;
import pl.Gruzdzis.SpringBoot_Gallery_APP.Repository.ImageRepository;
import pl.Gruzdzis.SpringBoot_Gallery_APP.Service.ImageUploader;


import java.nio.file.Path;
import java.util.List;

@Controller
public class ImagesApi {

    @Autowired
    ImageUploader imageUploader;
    ImageRepository imageRepository;

    public ImagesApi(ImageUploader imageUploader, ImageRepository imageRepository) {
        this.imageUploader = imageUploader;
        this.imageRepository = imageRepository;
    }

    @GetMapping("/gallery")
    public String showGallery(Model model) {
        List<Image> imgUrls = imageRepository.findAll();
        model.addAttribute("imgUrls", imgUrls);

        return "gallery";
    }

    @GetMapping("/upload")
    public String showUpload() {
        return "upload";
    }

    @GetMapping("/")
    public String index() {
        return "welcome";
    }

    @PostMapping("/uploadFile")
    public String uploadFile(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {


        Path path = imageUploader.getPath(file);
        imageUploader.uploadFile(path);


        redirectAttributes.addFlashAttribute("message",
                "You successfully uploaded " + file.getOriginalFilename() + "!");

        return "redirect:/upload";
    }

}
