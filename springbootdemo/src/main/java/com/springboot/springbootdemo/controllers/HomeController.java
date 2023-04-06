package com.springboot.springbootdemo.controllers;

import com.springboot.springbootdemo.entities.Categories;
import com.springboot.springbootdemo.entities.Countries;
import com.springboot.springbootdemo.entities.ShopItems;
import com.springboot.springbootdemo.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController{

    @Autowired
    private ItemService itemService;

    @GetMapping(value = "/")
    public String index(Model model){
        List<ShopItems> items= itemService.getAllItems();
        model.addAttribute("tovary",items);

        List<Countries> countries=itemService.getAllCountries();
        model.addAttribute("countries",countries);

        return "index";
    }
    @GetMapping(value = "/about")
    public String about(){
        return "about";
    }
    @PostMapping(value = "/additem")
    public String addItem(@RequestParam(name = "country_id",defaultValue = "0") Long countryId,
                          @RequestParam(name = "item_name",defaultValue = "No Item") String name,
                          @RequestParam(name = "item_price",defaultValue = "0") int price,
                          @RequestParam(name = "item_amount",defaultValue = "0") int amount){

        Countries cnt = itemService.getCountry(countryId);
        if(cnt!=null){
            ShopItems item = new ShopItems();
            item.setName(name);
            item.setPrice(price);
            item.setAmount(amount);
            item.setCountry(cnt);
            itemService.addItem(item);
        }

        return "redirect:/";
    }
    @GetMapping(value = "/details/{idshka}")
    public String details(Model model,@PathVariable(name="idshka") Long id){
        ShopItems item = itemService.getItem(id);
        model.addAttribute("item",item);

        List<Countries> countries=itemService.getAllCountries();
        model.addAttribute("countries",countries);

        List<Categories> categories=itemService.getAllCategories();
        model.addAttribute("categories",categories);

        return "details";
    }

    @PostMapping(value = "/saveitem")
    public String saveItem( @RequestParam(name = "id",defaultValue = "0") Long id,
                            @RequestParam(name = "country_id",defaultValue = "0") Long countryId,
                           @RequestParam(name = "item_name",defaultValue = "No Item") String name,
                           @RequestParam(name = "item_price",defaultValue = "0") int price,
                           @RequestParam(name = "item_amount",defaultValue = "0") int amount){

        ShopItems item =itemService.getItem(id);
        if(item!=null){
            Countries cnt = itemService.getCountry(countryId);
            if(cnt!=null) {

                item.setName(name);
                item.setAmount(amount);
                item.setPrice(price);
                item.setCountry(cnt);
                itemService.saveItem(item);
            }
        }
        return "redirect:/";
    }
    @PostMapping(value = "/deleteitem")
    public String deleteItem( @RequestParam(name = "id",defaultValue = "0") Long id){

        ShopItems item =itemService.getItem(id);
        if(item!=null){
            itemService.deleteItem(item);
        }
        return "redirect:/";
    }
    @PostMapping(value = "/assigncategory")
    public String assignCategory(@RequestParam(name = "item_id") Long itemId,
                                 @RequestParam(name="category_id")Long categoryId){
        Categories cat=itemService.getCategory(categoryId);
        if(cat!=null){
            ShopItems item=itemService.getItem(itemId);
            if (item!=null){

                List<Categories> categories=item.getCategories();
                if (categories==null){
                    categories=new ArrayList<>();
                }
                categories.add(cat);
                itemService.saveItem(item);
                return "redirect:/details/"+itemId;
            }
        }
        return "redirect:/";
    }
    @GetMapping(value="/403")
    public String accessDenied(Model model){
        return "403";
    }

}