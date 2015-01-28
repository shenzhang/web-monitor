package io.github.shenzhang.monitor.spring;

import io.github.shenzhang.monitor.DefaultMonitorAspect;
import org.springframework.aop.aspectj.AspectJAroundAdvice;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.aspectj.AspectJPointcutAdvisor;
import org.springframework.aop.config.AopNamespaceUtils;
import org.springframework.aop.config.MethodLocatingFactoryBean;
import org.springframework.aop.config.SimpleBeanFactoryAwareAspectInstanceFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanReference;
import org.springframework.beans.factory.config.ConstructorArgumentValues;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;

import java.util.ArrayList;
import java.util.List;

/**
 * User: shenzhang
 * Date: 1/23/15
 * Time: 2:38 PM
 */
public class MonitorBeanDefinitionParser implements BeanDefinitionParser {
    private static final String ASPECT_METHOD = "run";
    private static int monitorIndex = 0;

    @Override
    public synchronized BeanDefinition parse(Element element, ParserContext parserContext) {
        monitorIndex++;

        configureAutoProxyCreator(parserContext, element);

        BeanDefinitionRegistry registry = parserContext.getRegistry();
        registry.registerBeanDefinition(aspectBeanName(), createAspectBean(parserContext));

        List<BeanDefinition> beanDefinitions = new ArrayList<BeanDefinition>();
        List<BeanReference> beanReferences = new ArrayList<BeanReference>();


        AbstractBeanDefinition adviceDefinition = createMonitorAdvice(element, parserContext, beanDefinitions, beanReferences);
        beanDefinitions.add(adviceDefinition);

        return null;
    }

    private AbstractBeanDefinition createAspectBean(ParserContext parserContext) {
        AbstractBeanDefinition aspectBean = new RootBeanDefinition(DefaultMonitorAspect.class);



        return aspectBean;
    }

    private String aspectBeanName() {
        return "monitor-aspect-bean-" + monitorIndex;
    }

    private AbstractBeanDefinition createMonitorAdvice(Element element, ParserContext parserContext, List<BeanDefinition> beanDefinitions,
                                                       List<BeanReference> beanReferences) {
        // create the method factory bean
        RootBeanDefinition methodDefinition = new RootBeanDefinition(MethodLocatingFactoryBean.class);
        methodDefinition.getPropertyValues().add("targetBeanName", aspectBeanName());
        methodDefinition.getPropertyValues().add("methodName", ASPECT_METHOD);
        methodDefinition.setSynthetic(true);

        // create instance factory definition
        RootBeanDefinition aspectFactoryDef = new RootBeanDefinition(SimpleBeanFactoryAwareAspectInstanceFactory.class);
        aspectFactoryDef.getPropertyValues().add("aspectBeanName", aspectBeanName());
        aspectFactoryDef.setSynthetic(true);

        // register the pointcut
        AbstractBeanDefinition adviceDef = createAdviceDefinition(element, parserContext, methodDefinition, aspectFactoryDef, beanDefinitions, beanReferences);

        // configure the advisor
        RootBeanDefinition advisorDefinition = new RootBeanDefinition(AspectJPointcutAdvisor.class);
//            advisorDefinition.setSource(parserContext.extractSource(adviceElement));
        advisorDefinition.getConstructorArgumentValues().addGenericArgumentValue(adviceDef);

        // register the final advisor
        parserContext.getReaderContext().registerWithGeneratedName(advisorDefinition);

        return advisorDefinition;
    }

    private AbstractBeanDefinition createAdviceDefinition(Element element, ParserContext parserContext, RootBeanDefinition methodDef,
                                                          RootBeanDefinition aspectFactoryDef, List<BeanDefinition> beanDefinitions,
                                                          List<BeanReference> beanReferences) {
        RootBeanDefinition adviceDefinition = new RootBeanDefinition(AspectJAroundAdvice.class);
//        adviceDefinition.setSource(parserContext.extractSource(adviceElement));

        adviceDefinition.getPropertyValues().add("aspectName", aspectBeanName());
//        adviceDefinition.getPropertyValues().add(DECLARATION_ORDER_PROPERTY, order);

        ConstructorArgumentValues cav = adviceDefinition.getConstructorArgumentValues();
        cav.addIndexedArgumentValue(0, methodDef);

        AbstractBeanDefinition pointcut = createPointcutDefinition(element);
        cav.addIndexedArgumentValue(1, pointcut);
        beanDefinitions.add(pointcut);

        cav.addIndexedArgumentValue(2, aspectFactoryDef);

        return adviceDefinition;
    }

    private AbstractBeanDefinition createPointcutDefinition(Element element) {
        String expression = element.getAttribute("pointcut");

        RootBeanDefinition beanDefinition = new RootBeanDefinition(AspectJExpressionPointcut.class);
        beanDefinition.setScope(BeanDefinition.SCOPE_PROTOTYPE);
        beanDefinition.setSynthetic(true);
        beanDefinition.getPropertyValues().add("expression", expression);
        return beanDefinition;
    }

    private void configureAutoProxyCreator(ParserContext parserContext, Element element) {
        AopNamespaceUtils.registerAspectJAutoProxyCreatorIfNecessary(parserContext, element);
    }
}
