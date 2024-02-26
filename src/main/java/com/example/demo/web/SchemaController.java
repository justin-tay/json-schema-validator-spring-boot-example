package com.example.demo.web;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.networknt.schema.InputFormat;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.OutputFormat;
import com.networknt.schema.PathType;
import com.networknt.schema.SchemaLocation;
import com.networknt.schema.SchemaValidatorsConfig;
import com.networknt.schema.SpecVersion.VersionFlag;
import com.networknt.schema.output.OutputUnit;

@Controller
@RequestMapping("/schema")
public class SchemaController {

	private final JsonSchema schema;

	public SchemaController() {
		SchemaValidatorsConfig config = new SchemaValidatorsConfig();
		config.setPathType(PathType.JSON_POINTER);
		config.setOpenAPI3StyleDiscriminators(true);
		this.schema = JsonSchemaFactory.getInstance(VersionFlag.V6)
				.getSchema(SchemaLocation.of("classpath:schemas/schema.json"), config);
	}

	@PostMapping(value = "/validate", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<OutputUnit> validate(@RequestBody String input) {
		return ResponseEntity.ok(schema.validate(input, InputFormat.JSON, OutputFormat.HIERARCHICAL));
	}
}
