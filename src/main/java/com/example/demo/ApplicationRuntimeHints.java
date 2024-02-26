package com.example.demo;

import org.springframework.aot.hint.RuntimeHints;
import org.springframework.aot.hint.RuntimeHintsRegistrar;

public class ApplicationRuntimeHints implements RuntimeHintsRegistrar {
	@Override
	public void registerHints(RuntimeHints hints, ClassLoader classLoader) {
		hints.resources().registerPattern("schemas/schema.json");
	}
}
