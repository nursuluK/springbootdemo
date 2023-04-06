package com.springboot.springbootdemo.services.impl;

import com.springboot.springbootdemo.entities.Categories;
import com.springboot.springbootdemo.entities.Countries;
import com.springboot.springbootdemo.entities.ShopItems;
import com.springboot.springbootdemo.repositories.CategoryRepository;
import com.springboot.springbootdemo.repositories.CountryRepository;
import com.springboot.springbootdemo.repositories.ShopItemRepository;
import com.springboot.springbootdemo.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ShopItemRepository shopItemRepository;


    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public ShopItems addItem(ShopItems item) {
        return shopItemRepository.save(item);
}

    @Override
    public List<ShopItems> getAllItems() {
        return shopItemRepository.findAllByAmountIsGreaterThanOrderByPriceDesc(0);
    }

    @Override
    public ShopItems getItem(Long id) {
        return shopItemRepository.findByIdAndAmountGreaterThan(id,0);
    }

    @Override
    public void deleteItem(ShopItems item) {
        shopItemRepository.delete(item);
    }

    @Override
    public ShopItems saveItem(ShopItems item) {
        return shopItemRepository.save(item);
    }

    @Override
    public List<Countries> getAllCountries() {
        return countryRepository.findAll();
    }

    @Override
    public Countries addCountry(Countries country) {
        return countryRepository.save(country);
    }

    @Override
    public Countries saveCountry(Countries country) {
        return countryRepository.save(country);
    }

    @Override
    public Countries getCountry(Long id) {
        return countryRepository.getOne(id);
    }

    @Override
    public List<Categories> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Categories getCategory(Long id) {
        return categoryRepository.getOne(id);
    }

    @Override
    public Categories saveCategory(Categories category) {
        return categoryRepository.save(category);
    }

    @Override
    public Categories addCategory(Categories category) {
        return categoryRepository.save(category);
    }
}
