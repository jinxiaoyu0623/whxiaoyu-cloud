package com.whxiaoyu.component.oss;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author jinxiaoyu
 */
@Configuration(proxyBeanMethods = false)
@ConditionalOnMissingBean({OssProvider.class})
@EnableConfigurationProperties({OssProperties.class})
@Import({OssAutoConfiguration.OssConfigurationImportSelector.class})
public class OssAutoConfiguration {


    /**
     * {@link ImportSelector} to add {@link OssType} configuration classes.
     */
    static class OssConfigurationImportSelector implements ImportSelector {

        @Override
        public String[] selectImports(AnnotationMetadata importingClassMetadata) {
            OssType[] types = OssType.values();
            String[] imports = new String[types.length];
            for (int i = 0; i < types.length; i++) {
                imports[i] = OssConfigurations.getConfigurationClass(types[i]);
            }
            return imports;
        }

    }
}
