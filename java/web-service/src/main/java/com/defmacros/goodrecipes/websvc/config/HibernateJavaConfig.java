package com.defmacros.goodrecipes.websvc.config;

import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.boot.autoconfigure.AutoConfigurationPackages;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.domain.EntityScanPackages;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.persistenceunit.DefaultPersistenceUnitManager;
import org.springframework.util.StringUtils;

import javax.persistence.spi.PersistenceUnitInfo;
import java.util.List;
import java.util.Map;

@Configuration
@AutoConfigureAfter({ HibernateJpaAutoConfiguration.class })
public class HibernateJavaConfig {

    @ConditionalOnMissingBean({ Metadata.class })
    @Bean
    public Metadata getMetadata(StandardServiceRegistry standardServiceRegistry,
                                PersistenceUnitInfo persistenceUnitInfo) {
        MetadataSources metadataSources = new MetadataSources(standardServiceRegistry);

        List<String> managedClassNames = persistenceUnitInfo.getManagedClassNames();
        for (String managedClassName : managedClassNames) {
            metadataSources.addAnnotatedClassName(managedClassName);
        }

        return metadataSources.buildMetadata();
    }

    @ConditionalOnMissingBean({ StandardServiceRegistry.class })
    @Bean
    public StandardServiceRegistry getStandardServiceRegistry(JpaProperties jpaProperties) {
        StandardServiceRegistryBuilder ssrb = new StandardServiceRegistryBuilder();

        Map<String, String> properties = jpaProperties.getProperties();
        ssrb.applySettings(properties);

        return ssrb.build();
    }

    @ConditionalOnMissingBean({ PersistenceUnitInfo.class })
    @Bean
    public PersistenceUnitInfo getPersistenceUnitInfo(BeanFactory beanFactory) {
        List<String> packagesToScan = EntityScanPackages.get(beanFactory).getPackageNames();
        if (packagesToScan.isEmpty() && AutoConfigurationPackages.has(beanFactory)) {
            packagesToScan = AutoConfigurationPackages.get(beanFactory);
        }

        DefaultPersistenceUnitManager persistenceUnitManager = new DefaultPersistenceUnitManager();

        String[] packagesToScanArr = StringUtils.toStringArray(packagesToScan);
        persistenceUnitManager.setPackagesToScan(packagesToScanArr);
        persistenceUnitManager.afterPropertiesSet();

        return persistenceUnitManager.obtainDefaultPersistenceUnitInfo();
    }
}
