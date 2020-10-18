package org.magnum.mobilecloud.video;

import org.magnum.mobilecloud.video.repository.Video;
import org.magnum.mobilecloud.video.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class VideoServiceImpl implements VideoService {

    @Autowired
    private VideoRepository repository;


    @Override
    public Collection<Video> getAllVideos() {
        return (Collection<Video>) repository.findAll();
    }

    @Override
    public Video getVideoById(long id) {
        return repository.findOne(id);
    }

    @Override
    public Video createVideo(Video v) {
        return repository.save(v);
    }

    @Override
    public Collection<Video> findVideosByName(String title) {
        return repository.findByName(title);
    }

    @Override
    public Collection<Video> findByDurationLessThan(long duration) {
        return repository.findByDurationLessThan(duration);
    }

    @Override
    public Boolean like(long id, String name) {
        Video video = repository.findOne(id);
        if (video == null)
            return null;
        if (video.getLikedBy().contains(name))
            return false;
        video.setLikes(video.getLikes() + 1);
        video.getLikedBy().add(name);
        repository.save(video);
        return true;
    }

    @Override
    public Boolean unLike(long id, String name) {
        Video video = repository.findOne(id);
        if (video == null)
            return null;
        if (!video.getLikedBy().contains(name))
            return false;
        video.setLikes(video.getLikes() - 1);
        video.getLikedBy().remove(name);
        repository.save(video);
        return true;
    }
}
