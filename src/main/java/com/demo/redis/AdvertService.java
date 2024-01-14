package com.demo.redis;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@EnableCaching
public class AdvertService {

    private final AdvertRepository advertRepository;
    @CacheEvict(value = CacheName.ADVERT, allEntries = true, condition = "#request.name() != null")
    public CreateAdvertResponse create(CreateAdvertRequest request){
        Advert advert = new Advert(request.name(), request.title(), request.price());
        Advert save = advertRepository.save(advert);
        return CreateAdvertResponse.builder()
                .id(save.getId())
                .title(save.getTitle())
                .name(save.getName())
                .price(save.getPrice())
                .build();
    }
    @CacheEvict(value = CacheName.ADVERT, allEntries = true, condition = "#id != null")
    public void delete(String id){
        advertRepository.delete(findById(id));
    }
    @CacheEvict(value = CacheName.ADVERT, allEntries = true, condition = "#request.id() != null")
    public void update(UpdateAdvertRequest request){
        Advert advert = findById(request.id());
        advert.setName(request.name().trim().isEmpty() ? advert.getName() : request.name());
        advert.setTitle(request.title().trim().isEmpty() ? advert.getTitle() : request.title());
        advert.setPrice(request.price());
        advertRepository.save(advert);
    }

    @Cacheable(CacheName.ADVERT)
    public List<Advert> findAll(){
        System.out.println("cache sistemi calismiyor.");
        return advertRepository.findAll();
    }

    private Advert findById(String id) {
        return advertRepository.findById(id).orElseThrow(() -> new RuntimeException("not exist"));
    }
}
