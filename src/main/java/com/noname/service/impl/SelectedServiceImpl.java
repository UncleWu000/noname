package com.noname.service.impl;

import com.noname.entity.Selected;
import com.noname.mapper.SelectedMapper;
import com.noname.service.BaseService;
import com.noname.service.SelectedService;
import org.springframework.stereotype.Service;

@Service
public class SelectedServiceImpl extends BaseServiceImpl<SelectedMapper, Selected> implements SelectedService{
}
