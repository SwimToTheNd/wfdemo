package com.swimtothend.activiti.config;
//
//import org.activiti.engine.*;
//import org.activiti.engine.impl.cfg.ProcessEngineConfigurationImpl;
//import org.activiti.spring.ProcessEngineFactoryBean;
//import org.activiti.spring.SpringProcessEngineConfiguration;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.core.io.Resource;
//import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
//import org.springframework.transaction.PlatformTransactionManager;
//
//import javax.sql.DataSource;
//import java.io.IOException;
//
///**
// * create by BloodFly at 2019/3/21
// */
////@Configuration
//public class SpringProcessEngineConfig {
//
//    @Autowired
//    private DataSource dataSource;
//
//    @Autowired
//    private PlatformTransactionManager transactionManager;
//
//    @Bean
//    public ProcessEngineConfiguration processEngineConfiguration() throws IOException {
//        SpringProcessEngineConfiguration configuration = new SpringProcessEngineConfiguration();
//        configuration.setDataSource(dataSource);
//        configuration.setTransactionManager(transactionManager);
//        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
//        Resource[] resources = resolver.getResources("classpath:/processes/*.bpmn");
//        configuration.setDeploymentResources(resources);
//        return configuration;
//    }
//
//    @Bean
//    public ProcessEngine processEngineFactory(ProcessEngineConfigurationImpl configuration) throws Exception {
//        ProcessEngineFactoryBean factoryBean = new ProcessEngineFactoryBean();
//        factoryBean.setProcessEngineConfiguration(configuration);
//        return factoryBean.getObject();
//    }
//
//    @Bean
//    public RepositoryService repositoryService(ProcessEngine processEngine) {
//        return processEngine.getRepositoryService();
//    }
//
//    @Bean
//    public RuntimeService runtimeService(ProcessEngine processEngine) {
//        return processEngine.getRuntimeService();
//    }
//
//    @Bean
//    public FormService formService(ProcessEngine processEngine) {
//        return processEngine.getFormService();
//    }
//
//    @Bean
//    public IdentityService identityService(ProcessEngine processEngine) {
//        return processEngine.getIdentityService();
//    }
//
//    @Bean
//    public TaskService taskService(ProcessEngine processEngine) {
//        return processEngine.getTaskService();
//    }
//}
