package kg.attractor.microgram.controller;

import kg.attractor.microgram.dto.PublicationDto;
import kg.attractor.microgram.dto.UserDto;
import kg.attractor.microgram.service.PublicationService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class PublicationController {
private final PublicationService service;

    @GetMapping("/publications/{id}")
    public ResponseEntity<List<PublicationDto>> publications(@PathVariable String id) {
        int userId= Integer.parseInt(id);
        return new ResponseEntity<>(service.getAllPublication(userId), HttpStatus.OK);
    }
    @GetMapping("/publications/forMe/{id}")
    public ResponseEntity<List<PublicationDto>> publicationsForMe(@PathVariable String id){
        int userId= Integer.parseInt(id);
        return new ResponseEntity<>(service.getPublicationListForMe(userId), HttpStatus.OK);
    }
    @GetMapping("/publications/my/{id}")
    public ResponseEntity<List<PublicationDto>> myPublications(@PathVariable String id){
        int userId= Integer.parseInt(id);
        return new ResponseEntity<>(service.getMyListPublication(userId), HttpStatus.OK);
    }

}
