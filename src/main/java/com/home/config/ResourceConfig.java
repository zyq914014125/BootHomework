package com.home.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author Mr.X
 **/
@Component
@PropertySource("classpath:application.yml")
public class ResourceConfig {
        @Value("${spring.resource.path}")
        private String relativePath;
        @Value("${spring.resource.path.pattern}")
        private String relativePathPattern;
        @Value("${spring.resource.folder.windows}")
        private String locationPathForWindows;
        @Value("${spring.resource.folder.linux}")
        private String locationPathForLinux;

        public String getRelativePath() {
            return relativePath;
        }

        public void setRelativePath(String relativePath) {
            this.relativePath = relativePath;
        }

        public String getRelativePathPattern() {
            return relativePathPattern;
        }

        public void setRelativePathPattern(String relativePathPattern) {
            this.relativePathPattern = relativePathPattern;
        }

        public String getLocationPathForWindows() {
            return locationPathForWindows;
        }

        public void setLocationPathForWindows(String locationPathForWindows) {
            this.locationPathForWindows = locationPathForWindows;
        }

        public String getLocationPathForLinux() {
            return locationPathForLinux;
        }

        public void setLocationPathForLinux(String locationPathForLinux) {
            this.locationPathForLinux = locationPathForLinux;
        }
    }

