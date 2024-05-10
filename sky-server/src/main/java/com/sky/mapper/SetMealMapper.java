package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.Annotation.AutoFill;
import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.entity.Setmeal;
import com.sky.entity.SetmealDish;
import com.sky.enumeration.OperationType;
import com.sky.vo.DishItemVO;
import com.sky.vo.SetmealVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SetMealMapper {

    /**
     * 插入一条套餐数据
     * @param setmeal
     */
    @AutoFill(OperationType.INSERT)
    void insert(Setmeal setmeal);

    /**
     *查询该分类下所有的套餐数量
     * @param id
     * @return
     */
    @Select("select  count(*) from setmeal  where category_id=#{id}")
    Integer countByCategoryId(Long id);

    /**
     * 套餐的分页查询
     * @param setmealPageQueryDTO
     * @return
     */
    Page<SetmealVO> pageQuery(SetmealPageQueryDTO setmealPageQueryDTO);

    /**
     * 根据id查询套餐，带有分类名字，以及关联的菜品
     * @param id
     * @return
     */
    SetmealVO getById(Long id);

    /**
     * 更新套餐
     * @param setmeal
     */
    @AutoFill(OperationType.UPDATE)
    void update(Setmeal setmeal);

    /**
     * 查询正在售卖的套餐数量
     * @param ids
     * @return
     */
    Integer countBySetmealIds(List<Long> ids);

    /**
     * 删除多组套餐
     * @param ids
     */
    void deleteByIds(List<Long> ids);

    /**
     * 根据分类id查询套餐列表
     * @param categoryId
     * @return
     */
    @Select("select * from setmeal where category_id=#{categoryId}")
    List<Setmeal> getByCategoryId(Long categoryId);
    /**
     * 根据套餐id查询菜品选项,涉及到多表查询
     * @param setMealId
     * @return
     */
    @Select("select sd.name, sd.copies, d.image, d.description " +
            "from setmeal_dish sd left join dish d on sd.dish_id = d.id " +
            "where sd.setmeal_id = #{setMealId}")
    List<DishItemVO> getBySetmealId(Long setMealId);

    /**
     * 动态条件查询套餐
     * @param setmeal
     * @return
     */
    List<Setmeal> list(Setmeal setmeal);
}