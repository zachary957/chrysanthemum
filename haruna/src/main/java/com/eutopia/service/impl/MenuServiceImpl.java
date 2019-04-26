package com.eutopia.service.impl;

import com.eutopia.DTO.MenuDTO;
import com.eutopia.entity.Menu;
import com.eutopia.mapper.MenuMapper;
import com.eutopia.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public Integer addMenu(MenuDTO menuDTO) {
        Menu menu = new Menu();
        BeanCopier beanCopier = BeanCopier.create(MenuDTO.class, Menu.class, false);
        beanCopier.copy(menuDTO, menu, null);
        menu.setGmtCreate(new Date());
        menuMapper.insertSelective(menu);
        return menu.getId();
    }
}

