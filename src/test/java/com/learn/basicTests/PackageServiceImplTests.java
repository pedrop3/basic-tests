package com.learn.basicTests;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.learn.basicTests.model.PackageGLS;
import com.learn.basicTests.model.Person;
import com.learn.basicTests.model.Person.TypePersonEnum;
import com.learn.basicTests.repository.PackageRepository;
import com.learn.basicTests.service.impl.PackageServiceImlp;
import com.learn.basicTests.service.impl.PersonServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class PackageServiceImplTests {

    @InjectMocks
    private PackageServiceImlp packageServiceImlp;

    @Mock
    private PackageRepository packageRepository;

    @Mock
    private PersonServiceImpl personServiceImpl;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);

    }

    @Test
    public void shouldSavePackage() {

        var packageMock = buildPackageGLS();

        when(personServiceImpl.findById(1L)).thenReturn(buildSender());
        when(personServiceImpl.findById(2L)).thenReturn(buildRecipient());

        when(packageRepository.save(any())).thenReturn(packageMock);

        var packageSaved = packageServiceImlp.save(packageMock);

        assertEquals(packageMock, packageSaved);
    }

    public static PackageGLS buildPackageGLS() {
        return PackageGLS.builder()
                .id(1L)
                .sender(buildSender())
                .recipient(buildRecipient())
                .price(50.00)
                .build();
    }

    public static Person buildSender() {
        return Person.builder()
                .id(1L)
                .name("Pedro")
                .cpf("08790298624")
                .typePersonEnum(TypePersonEnum.SENDER)
                .build();
    }

    public static Person buildRecipient() {
        return Person.builder()
                .id(2L)
                .name("Carlos")
                .cpf("08790298624")
                .typePersonEnum(TypePersonEnum.RECIPIENT)
                .build();
    }

}
