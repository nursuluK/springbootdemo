package com.springboot.springbootdemo.repositories;

import com.springboot.springbootdemo.entities.ShopItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
@Repository
@Transactional
public interface ShopItemRepository extends JpaRepository<ShopItems,Long> {


    List<ShopItems> findAllByAmountIsGreaterThanOrderByPriceDesc(int amount);
    ShopItems findByIdAndAmountGreaterThan(Long id,int amount);


}
