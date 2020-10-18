package org.magnum.mobilecloud.video;


import org.magnum.mobilecloud.video.repository.Video;

import java.util.Collection;

public interface VideoService {


    Collection<Video> getAllVideos();

    Video getVideoById(long id);

    Video createVideo(Video v);

    Collection<Video> findVideosByName(String title);

    Collection<Video> findByDurationLessThan(long duration);

    Boolean like(long id, String name);

    Boolean unLike(long id, String name);
}
