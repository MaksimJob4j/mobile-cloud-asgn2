package org.magnum.mobilecloud.video;

import org.magnum.mobilecloud.video.client.VideoSvcApi;
import org.magnum.mobilecloud.video.repository.Video;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Collection;

import static org.magnum.mobilecloud.video.client.VideoSvcApi.*;

@Controller
public class VideoController {

    @Autowired
    VideoService service;

    @RequestMapping(value = VideoSvcApi.VIDEO_SVC_PATH, method = RequestMethod.GET)
    public @ResponseBody Collection<Video> getVideoList() {
        return service.getAllVideos();

    }

    @RequestMapping(value = VideoSvcApi.VIDEO_SVC_PATH + "/{id}", method = RequestMethod.GET)
    public @ResponseBody Video getVideoById(@PathVariable("id") long id) {
        return service.getVideoById(id);
    }

    @RequestMapping(value = VideoSvcApi.VIDEO_SVC_PATH, method = RequestMethod.POST)
    public @ResponseBody Video addVideo(@RequestBody Video v) {
        return service.createVideo(v);
    }

    @RequestMapping(value = VIDEO_SVC_PATH + "/{id}/like", method = RequestMethod.POST)
    public ResponseEntity likeVideo(@PathVariable("id") long id, Principal principal) {
        return handleLikeResult(service.like(id, principal.getName()));
    }

    @RequestMapping(value = VIDEO_SVC_PATH + "/{id}/unlike", method = RequestMethod.POST)
    public ResponseEntity unlikeVideo(@PathVariable("id") long id, Principal principal) {
        return handleLikeResult(service.unLike(id, principal.getName()));
    }

    private ResponseEntity handleLikeResult(Boolean like) {
        if (like == null)
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        if (like)
            return new ResponseEntity(HttpStatus.OK);
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = VIDEO_TITLE_SEARCH_PATH, method = RequestMethod.GET)
    public @ResponseBody Collection<Video> findByTitle(@RequestParam(TITLE_PARAMETER) String title) {
        return service.findVideosByName(title);

    }

    @RequestMapping(value = VIDEO_DURATION_SEARCH_PATH, method = RequestMethod.GET)
    public @ResponseBody Collection<Video> findByDurationLessThan(@RequestParam(DURATION_PARAMETER) long duration) {
        return service.findByDurationLessThan(duration);
    }
}
