package com.example.kafkaHandson;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class KafkaHandsonApplication {
	@Autowired
	private KafkaTemplate<String, Object> template;

	private String topic = "kafkatif";

	@GetMapping("/publish/{name}")
	public String publishMessage(@PathVariable String name) {
		template.send(topic, "Hi " + name + " Welcome to kafka");
		return "Data published";
	}

	@GetMapping("/publishJson")
	public String publishMessage() {
		User user = new User(2532, "User88", new String[] { "Bangalore", "BTM", "house 90" });
		template.send(topic, user);
		return "Json Data published";
	}

	public static void main(String[] args) {
		SpringApplication.run(KafkaHandsonApplication.class, args);
	}

}
