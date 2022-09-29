package kg.attractor.microgram.service;

import kg.attractor.microgram.dao.LikeDao;
import kg.attractor.microgram.entity.Like;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Builder
public class LikeService {
    private final LikeDao likeDao;
    public Boolean isLiked(int userId, int publicationId){
        List<Like> likes = likeDao.getLike(userId, publicationId);
        if (likes.size()>0){
            return true;
        }else return false;
    }

}
