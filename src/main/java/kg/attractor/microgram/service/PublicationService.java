package kg.attractor.microgram.service;

import kg.attractor.microgram.dao.PublicationDao;
import kg.attractor.microgram.dto.PublicationDto;
import kg.attractor.microgram.entity.Publication;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Builder
public class PublicationService {
    private final PublicationDao publicationDao;

    public List<PublicationDto> getAllMyPublication(int id){
List<Publication> publications = publicationDao.getAllMyPublication(id);

 List<PublicationDto> publicationsDto = new ArrayList<>();
        for (Publication pb:publications) {
            PublicationDto publicationDto = makePublicationDto(pb);
           publicationsDto.add(publicationDto);
        }
        return publicationsDto;
    }
    public List<PublicationDto> getAllPublication(){
List<Publication> publications = publicationDao.getAllPublication();

 List<PublicationDto> publicationsDto = new ArrayList<>();
        for (Publication pb:publications) {
            PublicationDto publicationDto = makePublicationDto(pb);
           publicationsDto.add(publicationDto);
        }
        return publicationsDto;
    }


    public List<PublicationDto> getPublicationListForMe(int id){
        List<Publication> publications=publicationDao.getPublicationListForMe(id);
        List<PublicationDto> publicationsDto = new ArrayList<>();
        for (Publication pb:publications) {
            PublicationDto publicationDto = makePublicationDto(pb);
                    publicationsDto.add(publicationDto);
        }
        return publicationsDto;
    }

    public static PublicationDto makePublicationDto(Publication pb){
        PublicationDto publicationDto = new PublicationDto();
        publicationDto.setId(pb.getId());
        publicationDto.setUser(UserService.makeUserDto(pb.getUser()));
        publicationDto.setImageLink(pb.getImageLink());
        publicationDto.setDescription(pb.getDescription());
        publicationDto.setDateTime(pb.getPublicationDateTime());
        return publicationDto;
    }

    public PublicationDto addPublication(long userId, MultipartFile file, String description) {
        try {
            String link = "postfile/"+file.getOriginalFilename();
            Publication p= publicationDao.addPublication(userId, link, description);
            File path = new File("src\\main\\resources\\static\\postfile\\"+file.getOriginalFilename());
//               C:\Program Files Code\Java 12\Microgram\Microgram_Rest_JS\\                                             src/main/resources/static/images
            FileOutputStream output =new FileOutputStream(path);
            output.write(file.getBytes());
            output.close();
            return makePublicationDto(p);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println(e.getMessage());
            return new PublicationDto();
        }
    }

    public String deletePublication(int userId, int pubId) {
        try {
            publicationDao.deletePublication(userId, pubId);
            return "Ok";
        }catch (Exception e){
            return e.getMessage();
        }
    }
}
