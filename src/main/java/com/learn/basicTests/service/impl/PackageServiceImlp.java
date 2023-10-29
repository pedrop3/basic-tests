package com.learn.basicTests.service.impl;

import org.springframework.stereotype.Service;

import com.learn.basicTests.model.PackageGLS;
import com.learn.basicTests.model.Person;
import com.learn.basicTests.repository.PackageRepository;
import com.learn.basicTests.service.PackageService;
import com.learn.basicTests.service.PersonService;

@Service
public class PackageServiceImlp implements PackageService {

    private final PersonService personService;
    private final PackageRepository packageRepository;

    PackageServiceImlp(PackageRepository packageRepository, PersonService personService) {
        this.packageRepository = packageRepository;
        this.personService = personService;

    }

    public PackageGLS save(PackageGLS packageGLS) {
        Person sender =   personService.findById(packageGLS.getSenderId());
        Person recipient =   personService.findById(packageGLS.getRecipientId());
       
        packageGLS.setRecipient(recipient);
        packageGLS.setSender(sender);

        return  this.packageRepository.save(packageGLS);
    }

}
