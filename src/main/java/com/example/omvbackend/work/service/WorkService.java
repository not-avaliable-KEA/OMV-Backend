package com.example.omvbackend.work.service;

import com.example.omvbackend.factory.DtoFactory;
import com.example.omvbackend.work.model.Work;
import com.example.omvbackend.work.repository.WorkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Optional;

@Service

public class WorkService
{
WorkRepository repository;

public WorkService(WorkRepository repository){
    this.repository = repository;
    DtoFactory.setWorkService(this);
}

    public Work create(Work work){
        return repository.save(work);
    }

    public Optional<Work> get(long id){
        return repository.findById(id);
    }

    public List<Work> getAll(){
        return repository.findAll();
    }

    public Work update(Work work){

        Optional<Work> optionalWork=get(work.getId());

        if (optionalWork.isEmpty()) return null;
        else{
            optionalWork.get().update(work);
        }
        return repository.save(optionalWork.get());
    }



    public boolean delete(long id){

        Optional<Work> optionalWork = get(id);

        if (optionalWork.isPresent()){
            repository.delete(optionalWork.get());
            return true;
        } else {
            return false;
        }
    }


}
