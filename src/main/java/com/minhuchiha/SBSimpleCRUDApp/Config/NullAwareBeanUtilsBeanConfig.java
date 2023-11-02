package com.minhuchiha.SBSimpleCRUDApp.Config;

import com.minhuchiha.SBSimpleCRUDApp.Utils.NullAwareBeanUtilsBean;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NullAwareBeanUtilsBeanConfig {
    @Bean
    public NullAwareBeanUtilsBean nullAwareBeanUtilsBean() {
        NullAwareBeanUtilsBean nullAwareBeanUtilsBean = new NullAwareBeanUtilsBean();
        return nullAwareBeanUtilsBean;
    }
}
