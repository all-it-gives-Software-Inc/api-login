package com.login.apilogin.service.impl;

import com.login.apilogin.domain.Address;
import com.login.apilogin.exception.BadRequestException;
import com.login.apilogin.mapper.AddressMapper;
import com.login.apilogin.repository.AddressRepository;
import com.login.apilogin.request.AddressRequestBody;
import com.login.apilogin.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    AddressRepository addressRepository;

    @Override
    public List<Address> listAll() {
        return addressRepository.findAll();
    }

    @Override
    public Address findById(Long id) {
        return addressRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Address Not Found!"));

    }

    @Override
    public List<Address> findByStreet(String street) {
        return addressRepository.findByStreet(street);

    }

    @Override
    public Address save(AddressRequestBody addressRequestBody) {
        return addressRepository.save(AddressMapper.INSTANCE.toAddress(addressRequestBody));
    }

    @Override
    public Address replace(AddressRequestBody addressRequestBody) {
        return addressRepository.save(AddressMapper.INSTANCE.toAddress(addressRequestBody));
    }
}
