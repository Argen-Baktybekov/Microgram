package kg.attractor.microgram.service;

import kg.attractor.microgram.dao.PublicationDao;
import kg.attractor.microgram.dto.PublicationDto;
import kg.attractor.microgram.dto.UserDto;
import kg.attractor.microgram.entity.Publication;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Builder
public class PublicationService {
    private final PublicationDao publicationDao;

    public List<PublicationDto> getAllPublication(int id){
List<Publication> publications = publicationDao.getAllPublication(id);

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
        publicationDto.setPublicationDateTime(pb.getPublicationDateTime());
        return publicationDto;
    }

    public String addPublication(int userId, MultipartFile file, String description) {
        try {
            String link = "images/"+file.getOriginalFilename();
            publicationDao.addPublication(userId, link, description);
            File path = new File("C:\\Program Files Code\\Java 12\\Microgram\\Microgram\\images\\"+file.getOriginalFilename());
            FileOutputStream output =new FileOutputStream(path);
            output.write(file.getBytes());
            output.close();
            return "Ok";
        }catch (Exception e){
            return e.getMessage();
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
