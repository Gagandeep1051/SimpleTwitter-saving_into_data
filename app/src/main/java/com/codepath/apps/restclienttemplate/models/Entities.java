package com.codepath.apps.restclienttemplate.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Collections;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Entities {
    private List<Media> media;

    public List<Media> getMedia() {
        return media == null ? Collections.<Media>emptyList() : media;
    }

    public void setMedia(List<Media> media) {
        this.media = media;
    }
}
