package concise.hackathon;

import at.favre.lib.crypto.bcrypt.BCrypt;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class LoadGeneratorApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoadGeneratorApplication.class, args);
	}

	@RestController
	public class BcryptController {

		private Logger logger = LoggerFactory.getLogger(BcryptController.class);

		@GetMapping
		public long getLoad(@RequestParam(defaultValue = "10") int cost) {
			StopWatch stopWatch = new StopWatch();
			stopWatch.start();
			String password = RandomStringUtils.random(30, true, true);
			String mypassword = BCrypt
					.withDefaults()
					.hashToString(cost, password.toCharArray());
			stopWatch.stop();
			logger.info("BCrypt hash generation took {} milliseconds", stopWatch.getTotalTimeMillis());

			return stopWatch.getTotalTimeMillis();
		}
	}
}

