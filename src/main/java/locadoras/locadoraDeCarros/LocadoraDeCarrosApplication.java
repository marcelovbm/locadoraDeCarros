package locadoras.locadoraDeCarros;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class LocadoraDeCarrosApplication {

	public static void main(String[] args) {
		SpringApplication.run(LocadoraDeCarrosApplication.class, args);
	}

	@Bean
	public RouteBuilder vendaInternaProcessingRoute() {

		return new RouteBuilder() {
			@Override
			public void configure() throws Exception {

				from("file://inputFileClienteRegular" + "?delete=true" + "&recursive=true" + "&exclude=^.*.tmp$"
						+ "&moveFailed=../error" + "/${file:onlyname.noext}.${file:ext}" + "&readLock=0" + "&delay=1000"
						+ "&readLock=changed" + "&readLockTimeout=65000" + "&sortBy=file:length")
								.to("bean:processInput?method=process");

				from("file://inputFileClienteFidelidade" + "?delete=true" + "&recursive=true" + "&exclude=^.*.tmp$"
						+ "&moveFailed=../error" + "/${file:onlyname.noext}.${file:ext}" + "&readLock=0" + "&delay=1000"
						+ "&readLock=changed" + "&readLockTimeout=65000" + "&sortBy=file:length")
								.to("bean:processInput?method=process");
			}
		};
	}
}
