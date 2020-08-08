package com.study.cheeper.cheep;

import com.study.cheeper.login.LoggedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        Cheep toDelete = this.cheepRepository.getOne(id);

        if(toDelete.isOwnedBy(loggedUser.asUser()))
            cheepRepository.delete(toDelete);
    }
}
