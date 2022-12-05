package com.example.omvbackend.livevideo.service;

import com.example.omvbackend.livevideo.model.LiveVideo;
import com.example.omvbackend.livevideo.repository.LiveVideoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class LiveVideoService {
    private final LiveVideoRepository repository;

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
    public LiveVideo update(LiveVideo liveVideo){
        Optional<LiveVideo> optionalLiveVideo=get(liveVideo.getId());

        if (optionalLiveVideo.isEmpty()) {
            return null;
        } else {
            optionalLiveVideo.get().update(liveVideo);
        }
        return repository.save(optionalLiveVideo.get());
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
