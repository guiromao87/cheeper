package com.study.cheeper.cheep;

import com.study.cheeper.login.LoggedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CheepService {

    @Autowired
    private CheepRepository cheepRepository;

    @Autowired
    private LoggedUser loggedUser;

    public void create(Cheep cheep) {
        cheepRepository.save(cheep);
    }

    public void delete(Long id) {
        Optional<Cheep> optionalCheep = this.cheepRepository.findById(id);

        if(optionalCheep.isPresent() && optionalCheep.get().isOwnedBy(loggedUser.asUser()))
            cheepRepository.delete(optionalCheep.get());
    }
}
