package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;


//Component везде?

    @Configuration
    @EnableTransactionManagement
    @ComponentScans(value = {
            @ComponentScan("dao"),
            @ComponentScan("service"),
            @ComponentScan("controller")//new
    })
   // @ComponentScan(basePackages = "dao, service, controller, config")
    @SuppressWarnings("unused")
    public class AppConfig {

    @Bean
    @SuppressWarnings("unused")
    public LocalEntityManagerFactoryBean getEntityManagerFactoryBean(){
        LocalEntityManagerFactoryBean factoryBean = new LocalEntityManagerFactoryBean();
        factoryBean.setPersistenceUnitName("LOCAL_PERSISTENCE");//???
        return factoryBean;
    }

    @Bean
    @SuppressWarnings("unused")
    public JpaTransactionManager getJpaTransactionManager(){
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(getEntityManagerFactoryBean().getObject());
        return transactionManager;
    }

}
