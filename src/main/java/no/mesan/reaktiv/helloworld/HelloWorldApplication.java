package no.mesan.reaktiv.helloworld;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import no.mesan.reaktiv.helloworld.health.TemplateHealthCheck;
import no.mesan.reaktiv.helloworld.resources.HelloWorldResource;

public class HelloWorldApplication extends Application<HelloWorldConfiguration> {
	public static void main(String[] args) throws Exception {
		new HelloWorldApplication().run(args);
	}

	@Override
	public String getName() {
		return "hello-world";
	}

	@Override
	public void initialize(Bootstrap<HelloWorldConfiguration> bootstrap) {
		// nothing to do yet
	}

	@Override
	public void run(HelloWorldConfiguration configuration,
			Environment environment) {
		final TemplateHealthCheck healthCheck = new TemplateHealthCheck(
				configuration.getTemplate());
		final HelloWorldResource resource = new HelloWorldResource(
				configuration.getTemplate(), configuration.getDefaultName());

		environment.healthChecks().register("template", healthCheck);
		environment.jersey().register(resource);
	}

}