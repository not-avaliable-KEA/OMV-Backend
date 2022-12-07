package com.example.omvbackend.livevideo.service;

import com.example.omvbackend.livevideo.model.LiveVideo;
import com.example.omvbackend.livevideo.repository.LiveVideoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class LiveVideoService {
    //we make if final/a constant so we cannot change it
    private final LiveVideoRepository repository;

    //constructor
    public LiveVideoService(LiveVideoRepository repository) {
        this.repository = repository;
    }

    //create
    public LiveVideo create(LiveVideo liveVideo){
        return repository.save(liveVideo);
    }

    //get
    public Optional<LiveVideo> get(long id){
        return repository.findById(id);
    }

    //getAll
    public List<LiveVideo> getAll(){
        return repository.findAll();
    }

    //update
    public Optional<LiveVideo> update(LiveVideo liveVideo){
        Optional<LiveVideo> optionalLiveVideo = repository.findById(liveVideo.getId());
        if(optionalLiveVideo.isEmpty()) {
            return null;
        }
        if(liveVideo.getUrl() !=null && !liveVideo.getUrl().isEmpty()){
            optionalLiveVideo.get().setUrl(liveVideo.getUrl());
        }
        if(liveVideo.getTitle() !=null && !liveVideo.getTitle().isEmpty()){
            optionalLiveVideo.get().setTitle(liveVideo.getTitle());
        }
        if(liveVideo.getIntro() !=null && !liveVideo.getIntro().isEmpty()){
            optionalLiveVideo.get().setIntro(liveVideo.getIntro());
        }
        if(liveVideo.getDate() != null){
            optionalLiveVideo.get().setDate(liveVideo.getDate());
        }

        return Optional.of(repository.save(optionalLiveVideo.get()));
    }

    //Delete
    public boolean delete(long id){
        Optional<LiveVideo> optionalLiveVideo = get(id);
        if(optionalLiveVideo.isPresent()){
            repository.delete(optionalLiveVideo.get());
            return true;
        }else{return false;

        }
    }
}
