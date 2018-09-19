package uy.edu.ude.BuscadorProyectos.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@EnableTransactionManagement
@ComponentScans(value = { 
	@ComponentScan("uy.edu.ude.BuscadorProyectos.service"),
	@ComponentScan("uy.edu.ude.BuscadorProyectos.service.interfaces"),
	@ComponentScan("uy.edu.ude.BuscadorProyectos.service.implementaciones"),	
    @ComponentScan("uy.edu.ude.BuscadorProyectos.dao"),
    @ComponentScan("uy.edu.ude.BuscadorProyectos.dao.interfaces"),
    @ComponentScan("uy.edu.ude.BuscadorProyectos.dao.implementaciones"),
})
public class AppConfig {

	
   @Bean
   public LocalEntityManagerFactoryBean geEntityManagerFactoryBean() {
      LocalEntityManagerFactoryBean factoryBean = new LocalEntityManagerFactoryBean();
      factoryBean.setPersistenceUnitName("LOCAL_PERSISTENCE");
      return factoryBean;
   }

   @Bean
   public JpaTransactionManager geJpaTransactionManager() {
      JpaTransactionManager transactionManager = new JpaTransactionManager();
      transactionManager.setEntityManagerFactory(geEntityManagerFactoryBean().getObject());
      return transactionManager;
   }

}