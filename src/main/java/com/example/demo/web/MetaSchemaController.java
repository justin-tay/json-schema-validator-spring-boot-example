package com.example.demo.web;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.networknt.schema.InputFormat;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.OutputFormat;
import com.networknt.schema.PathType;
import com.networknt.schema.SchemaId;
import com.networknt.schema.SchemaLocation;
import com.networknt.schema.SchemaValidatorsConfig;
import com.networknt.schema.SpecVersion.VersionFlag;
import com.networknt.schema.output.OutputUnit;

@Controller
@RequestMapping("/meta-schema")
public class MetaSchemaController {

	private final Logger logger = LoggerFactory.getLogger(MetaSchemaController.class);
	private final Map<String, JsonSchema> dialects = new HashMap<>();

	public MetaSchemaController() {
		SchemaValidatorsConfig config = new SchemaValidatorsConfig();
		config.setPathType(PathType.JSON_POINTER);
		JsonSchemaFactory factory = JsonSchemaFactory.getInstance(VersionFlag.V202012);

		dialects.put("2020-12", factory.getSchema(SchemaLocation.of(SchemaId.V202012), config));
		dialects.put("2019-09", factory.getSchema(SchemaLocation.of(SchemaId.V201909), config));
		dialects.put("07", factory.getSchema(SchemaLocation.of(SchemaId.V7), config));
		dialects.put("06", factory.getSchema(SchemaLocation.of(SchemaId.V6), config));
		dialects.put("04", factory.getSchema(SchemaLocation.of(SchemaId.V4), config));
	}

	@PostMapping(value = "/{dialect}/validate", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<OutputUnit> validate(@PathVariable String dialect, @RequestBody String input) {
		JsonSchema schema = dialects.get(dialect);
		if (schema == null) {
			logger.info("Cannot find dialect {}", dialect);
			return ResponseEntity.notFound().build();
		}
		logger.info("Validating using dialect {}", dialect);
		return ResponseEntity.ok(schema.validate(input, InputFormat.JSON, OutputFormat.HIERARCHICAL));
	}
}
