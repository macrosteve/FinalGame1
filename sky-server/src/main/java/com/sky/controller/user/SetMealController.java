package com.sky.controller.user;

import com.sky.entity.Setmeal;
import com.sky.result.Result;
import com.sky.service.SetMealService;
import com.sky.vo.DishItemVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("SetMealUserController")
@RequestMapping("/user/setmeal")
@Api(tags = "用户端套餐相关接口")
@Slf4j
public class SetMealController {
    @Autowired
    private SetMealService setMealService;
    @GetMapping("/list")
    @ApiOperation("根据分类id查询套餐")
    @Cacheable(cacheNames = "setmealCache",key = "#categoryId")
    public Result<List> getByCategoryId(Long categoryId){
    Setmeal setmeal=new Setmeal();
    setmeal.setCategoryId(categoryId);
    List<Setmeal> setmeals=setMealService.list(setmeal);
    return Result.success(setmeals);
    }
    @GetMapping("/dish/{id}")
    @ApiOperation("根据套餐id查询所有包含的菜品")
    public Result<List<DishItemVO>> getAllDishesById(@PathVariable Long id){
        List<DishItemVO> dishItems=setMealService.getDishesById(id);
        return Result.success(dishItems);
    }
}