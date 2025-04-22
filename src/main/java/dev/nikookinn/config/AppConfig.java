package dev.nikookinn.config;

import dev.nikookinn.annotations.MyComponent;
import dev.nikookinn.annotations.MyComponentScan;
/**
 * AppConfig - Main configuration class for the application
 * This class defines the base package for component scanning.
 */
@MyComponent
@MyComponentScan("dev.nikookinn")
public class AppConfig {
}
